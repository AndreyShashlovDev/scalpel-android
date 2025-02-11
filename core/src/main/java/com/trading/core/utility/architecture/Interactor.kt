package com.trading.core.utility.architecture

interface Interactor<T, R> {
    suspend operator fun invoke(params: T): R
}

interface NoParamsInteractor<R> {
    suspend operator fun invoke(): R
}