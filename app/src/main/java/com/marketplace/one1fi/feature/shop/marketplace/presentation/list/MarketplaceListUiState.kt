package com.marketplace.one1fi.feature.shop.marketplace.presentation.list

import com.marketplace.one1fi.feature.shop.marketplace.data.model.Product

data class MarketplaceListUiState(
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
) {
    val isEmpty: Boolean get() = !isLoading && errorMessage == null && products.isEmpty()
}
