package com.marketplace.one1fi.feature.shop.marketplace.data

import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.ProductDto

/**
 * Single contract both the mock and the real network implementation satisfy.
 * The repository only ever depends on this interface, so which one is
 * actually injected is purely a [com.onefi.app.feature.shop.marketplace.di.MarketplaceModule]
 * decision.
 */
interface MarketplaceDataSource {
    suspend fun fetchProducts(): List<ProductDto>
    suspend fun fetchProductDetail(productId: String): ProductDto
}
