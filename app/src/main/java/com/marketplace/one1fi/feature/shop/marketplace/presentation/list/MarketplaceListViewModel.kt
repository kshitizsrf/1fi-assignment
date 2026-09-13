package com.marketplace.one1fi.feature.shop.marketplace.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marketplace.one1fi.core.util.Resource
import com.marketplace.one1fi.feature.shop.marketplace.data.repository.MarketplaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketplaceListViewModel @Inject constructor(
    private val repository: MarketplaceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MarketplaceListUiState())
    val uiState: StateFlow<MarketplaceListUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun retry() = loadProducts()

    private fun loadProducts() {
        viewModelScope.launch {
            repository.getProducts().collect { resource ->
                _uiState.value = when (resource) {
                    is Resource.Loading -> _uiState.value.copy(
                        isLoading = true,
                        errorMessage = null
                    )

                    is Resource.Success -> _uiState.value.copy(
                        isLoading = false,
                        products = resource.data,
                        errorMessage = null
                    )

                    is Resource.Error -> _uiState.value.copy(
                        isLoading = false,
                        errorMessage = resource.message
                    )
                }
            }
        }
    }
}
