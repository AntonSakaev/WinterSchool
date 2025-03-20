package com.example.winterschool.screens.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetBooksInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.surf.retrofitlesson.presentation.screens.utils.handle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getBooksInfoUseCase: GetBooksInfoUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState = _uiState.asStateFlow()

    private val _keyText = MutableStateFlow("")
    private val keyText = _keyText.asStateFlow().debounce(2000).distinctUntilChanged()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            keyText.collectLatest { keyword ->
                if (keyword != "") {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = true
                        )
                    }
                    getBooksInfoUseCase.getBooksInfo(keyword).handle(
                        onSuccess = { postBooks ->
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    postBooks = postBooks,
                                    errorMessage = null
                                )
                            }
                        },
                        onError = ::onError
                    )
                } else {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun keywordInput(keyword: String) {
        _keyText.value = keyword
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
}