package id.haaweejee.saltnews.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.saltnews.domain.GetTopHeadlineInUSUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getTopHeadlineInUSUseCase: GetTopHeadlineInUSUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.initial())
    val state: StateFlow<NewsState> = _state

    fun event(event: NewsEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is NewsEvent.GetTopHeadlineNews -> {
                    _state.update {
                        it.copy(isLoading = true, error = null)
                    }

                    val result = getTopHeadlineInUSUseCase()

                    _state.update {
                        result.fold(
                            onSuccess = { data ->
                                it.copy(
                                    isLoading = false,
                                    data = data,
                                )
                            },
                            onFailure = { e ->
                                it.copy(
                                    isLoading = false,
                                    error = e,
                                )
                            },
                        )
                    }
                }
                is NewsEvent.GetInitialState -> NewsState.initial()
            }
        }
    }
}
