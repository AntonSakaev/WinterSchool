package com.example.data.local.database

import android.database.sqlite.SQLiteConstraintException
import androidx.room.EmptyResultSetException
import com.example.domain.remote.utils.OperationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
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
        } catch (e: EmptyResultSetException) {
            onError(DatabaseError.NotFound(e.message ?: "Not found"))
        } catch (e: IOException) {
            onError(DatabaseError.IO(e.message ?: "IO error"))
        } catch (e: Exception) {
            onError(DatabaseError.General(e.message ?: "Database error"))
        }
    }

    suspend fun <T> safeFlowDatabaseOperation(
        operation: suspend () -> Flow<T>
    ): Flow<OperationResult<T>> {
        return operation()
            .map { value -> OperationResult.Success(value) as OperationResult<T> }
            .catch { e ->
                val error = when (e) {
                    is SQLiteConstraintException -> DatabaseError.ConstraintViolation(
                        e.message ?: "Constraint violation"
                    )
                    is EmptyResultSetException -> DatabaseError.NotFound(e.message ?: "Not found")
                    is IOException -> DatabaseError.IO(e.message ?: "IO error")
                    else -> DatabaseError.General(e.message ?: "Database error")
                }
                emit(onError(error))
            }
            .onStart { emit(OperationResult.Loading) }
    }
}
