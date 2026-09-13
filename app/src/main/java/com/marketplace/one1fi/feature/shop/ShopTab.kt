package com.marketplace.one1fi.feature.shop

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

enum class ShopTab(val title: String, val icon: ImageVector) {
    MARKETPLACE("1Fi Marketplace", Icons.Filled.Storefront),
    TOP_BRANDS("Top Brands", Icons.Filled.Stars),
    NEARBY_STORES("Nearby Stores", Icons.Filled.LocationOn)
}
