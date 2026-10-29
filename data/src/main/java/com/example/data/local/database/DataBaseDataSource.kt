package com.example.data.local.database

import android.database.sqlite.SQLiteDatabaseCorruptException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

abstract class DatabaseDataSource {
    sealed class DatabaseError {
        data class General(val message: String) : DatabaseError()
        data class ConstraintViolation(val message: String) : DatabaseError()
        data class NotFound(val message: String) : DatabaseError()
        data class IO(val message: String) : DatabaseError()
    }

    fun handleDatabaseError(error: DatabaseError): String {
        return when (error) {
            is DatabaseError.General -> error.message
            is DatabaseError.ConstraintViolation -> error.message
            is DatabaseError.NotFound -> error.message
            is DatabaseError.IO -> error.message
        }
    }

    fun onError(error: DatabaseError): OperationResult.Error {
        return OperationResult.Error(handleDatabaseError(error))
    }

    suspend fun <T> safeDatabaseOperation(
        operation: suspend () -> T
    ): OperationResult<T> {
        return try {
            val result = operation()
            OperationResult.Success(result)
        } catch (e: SQLiteConstraintException) {
            onError(DatabaseError.ConstraintViolation(e.message ?: "Constraint violation"))
        } catch (e: IOException) {
            onError(DatabaseError.IO(e.message ?: "IO error"))
        } catch (e: Exception) {
            onError(DatabaseError.General(e.message ?: "Database error"))
        } catch (e: SQLiteDatabaseCorruptException) {
            onError(DatabaseError.General(e.message ?: "Database corrupt"))
        }
    }

    fun <T> safeFlowDatabaseOperation(
        operation: suspend () -> Flow<T>
    ): Flow<OperationResult<T>> {
        return flow {
            emit(OperationResult.Loading)
            try {
                operation().collect { value ->
                    emit(OperationResult.Success(value))
                }
            } catch (e: SQLiteConstraintException) {
                emit(onError(DatabaseError.ConstraintViolation(e.message ?: "Constraint violation")))
            } catch (e: IOException) {
                emit(onError(DatabaseError.IO(e.message ?: "IO error")))
            } catch (e: SQLiteDatabaseCorruptException) {
                emit(onError(DatabaseError.General(e.message ?: "Database corrupt")))
            } catch (e: Exception) {
                emit(onError(DatabaseError.General(e.message ?: "Database error")))
            }
        }
    }
}
