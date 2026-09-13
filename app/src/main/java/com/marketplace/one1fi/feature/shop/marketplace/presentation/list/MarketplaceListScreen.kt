package com.marketplace.one1fi.feature.shop.marketplace.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marketplace.one1fi.core.components.EmptyState
import com.marketplace.one1fi.core.components.ErrorState
import com.marketplace.one1fi.core.components.LoadingState
import com.marketplace.one1fi.feature.shop.marketplace.data.model.Product
import com.marketplace.one1fi.feature.shop.marketplace.presentation.list.components.MarketplaceHeaderBanner
import com.marketplace.one1fi.feature.shop.marketplace.presentation.list.components.ProductCard

/**
 * Entry point for the "1Fi Marketplace" tab on the Shop page. Hosted inside
 * [com.onefi.app.feature.shop.ShopScreen]'s pager — it owns its own nav
 * host (see [com.onefi.app.feature.shop.marketplace.navigation.MarketplaceNavGraph])
 * so drilling into a product doesn't disturb the Top Brands / Nearby Stores tabs.
 */
@Composable
fun MarketplaceListScreen(
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MarketplaceListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> LoadingState(modifier = modifier.fillMaxSize())
        uiState.errorMessage != null -> ErrorState(
            message = uiState.errorMessage.orEmpty(),
            onRetry = viewModel::retry,
            modifier = modifier.fillMaxSize()
        )

        uiState.isEmpty -> EmptyState(
            message = "No products available right now.",
            modifier = modifier.fillMaxSize()
        )

        else -> MarketplaceGrid(
            products = uiState.products,
            onProductClick = onProductClick,
            modifier = modifier
        )
    }
}

@Composable
private fun MarketplaceGrid(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
    ) {
        item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
            MarketplaceHeaderBanner(modifier = Modifier.padding(bottom = 4.dp))
        }
        items(products, key = { it.id }) { product ->
            ProductCard(product = product, onClick = onProductClick)
        }
    }
}
