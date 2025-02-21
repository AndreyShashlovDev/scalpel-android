package com.trading.feature_strategies.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trading.core.domain.network.model.reponse.ApiResult
import com.trading.core.utility.tag
import com.trading.feature_strategies.domain.repository.StrategyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StrategiesPagePresenter @Inject constructor(
    private val strategyRepository: StrategyRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val result = strategyRepository.getCompositeStrategies()

            if (result is ApiResult.Success) {
                Log.d(tag(this), "success")
            } else {
                Log.d(tag(this), "fail")
            }
        }
    }
}
