package com.example.presentation.screens.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Books
import com.example.domain.usecases.GetBooksInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.surf.retrofitlesson.presentation.screens.utils.handle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getBooksInfoUseCase: GetBooksInfoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState = _uiState.asStateFlow()

    private val _keyText = MutableStateFlow("")

    private val lastKnowKeyword = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    private val keyText = _keyText.asStateFlow().debounce(2000)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            keyText.collectLatest { keyword ->
                lastKnowKeyword.value = keyword
                launchSearch(keyword)
            }
        }
    }

    fun keywordInput(keyword: String) {
        _keyText.value = keyword
    }

    private suspend fun launchSearch(keyword: String) {
        if (keyword.isNotEmpty()) {
            getBooksInfoUseCase(keyword).collect {
                it.handle(
                    onSuccess = ::onSuccess,
                    onLoading = ::onLoading,
                    onError = ::onError
                )
            }
        } else {
            emptyKeyword()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            launchSearch(lastKnowKeyword.value)
        }
    }

    private fun onError(message: String) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                postBooks = null,
                errorMessage = message
            )
        }
     }

    private fun onLoading() {
        _uiState.update { state ->
            state.copy(
                isNoKeyWord = false,
                isLoading = true,
                postBooks = null,
                errorMessage = null
            )
        }
    }

    private fun onSuccess(postBooks: Books) {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                postBooks = postBooks,
                errorMessage = null
            )
        }
    }

    private fun emptyKeyword() {
        _uiState.update { state ->
            state.copy(
                isNoKeyWord = true,
                isLoading = false,
                postBooks = null,
                errorMessage = null
            )
        }
    }
}