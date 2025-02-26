package com.trading.feature_strategies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trading.core.domain.network.model.api.Pageable
import com.trading.core.domain.network.model.reponse.ApiResult
import com.trading.core.view.components.ListState
import com.trading.feature_strategies.domain.model.CompositeStrategy
import com.trading.feature_strategies.domain.repository.StrategyRepository
import com.trading.feature_strategies.presentation.mapper.CompositeStrategyUiMapper.toDomain
import com.trading.feature_strategies.presentation.model.StrategiesPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class StrategiesPagePresenter @Inject constructor(
    private val strategyRepository: StrategyRepository
) : ViewModel() {

    private var latestResponse: Pageable<CompositeStrategy>? = null
    private var fetchJob: Job? = null

    private val _state = MutableStateFlow(StrategiesPageState())
    val state = _state.asStateFlow()

    init {
        onFetchNext()
    }

    fun onFetchNext() {
        fetchJob?.cancel()

        _state.update {
            it.copy(
                listState = ListState.Loading
            )
        }

        fetchJob = viewModelScope.launch {
            try {
                val result = strategyRepository.getCompositeStrategies(
                    page = (latestResponse?.page ?: 0) + 1, limit = PAGE_LIMIT
                )

                if (!isActive) return@launch

                if (result is ApiResult.Success) {
                    latestResponse = result.data
                    val rawList = result.data.data

                    val items = _state.value.items + rawList.map { it.toDomain() }
                    val hasNext = rawList.size == PAGE_LIMIT && items.size < result.data.total

                    _state.update {
                        it.copy(
                            listState = ListState.Success(hasNext), items = items
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            listState = ListState.Error
                        )
                    }
                }
            } catch (e: CancellationException) {
                // ignore
            } finally {
                fetchJob = null
            }
        }
    }

    companion object {
        const val PAGE_LIMIT = 5
    }
}
