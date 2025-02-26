package com.trading.core.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

interface ListItem<T> {
    val id: T
}

sealed class ListState {
    data class Success(val hasFetchNext: Boolean) : ListState()
    data object Loading : ListState()
    data object ListEmpty : ListState()
    data object Error : ListState()
}

@Composable
fun <T : ListItem<*>> BaseListView(
    listState: ListState,
    items: List<T> = emptyList(),
    onFetchNext: () -> Unit = {},
    fetchNextThreshold: Int = 2,
    emptyContent: @Composable () -> Unit = {},
    errorContent: @Composable () -> Unit = {},
    itemContent: @Composable (index: Int) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxSize()
    ) {
        when (listState) {
            is ListState.ListEmpty -> {
                emptyContent()
            }

            is ListState.Error -> {
                errorContent()
            }

            is ListState.Success, is ListState.Loading -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp), state = lazyListState
                ) {
                    items(
                        count = items.size,
                        key = { index -> items[index].id!! }) { index -> itemContent(index) }

                    if (listState is ListState.Loading) {
                        item(key = "loading") {
                            AppLoadingView()
                        }
                    }
                }

                LaunchedEffect(lazyListState, items) {
                    snapshotFlow {
                        val lastVisibleItem =
                            lazyListState.firstVisibleItemIndex + lazyListState.layoutInfo.visibleItemsInfo.size
                        val totalItems = items.size
                        val hasFetchNext = listState is ListState.Success && listState.hasFetchNext

                        totalItems > 0 && (totalItems - lastVisibleItem) <= fetchNextThreshold && hasFetchNext
                    }.distinctUntilChanged()
                        .filter { it }
                        .collect { onFetchNext() }
                }
            }
        }
    }

}
