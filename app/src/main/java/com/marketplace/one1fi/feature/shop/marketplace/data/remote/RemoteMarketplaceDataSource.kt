package com.marketplace.one1fi.feature.shop.marketplace.data.remote

import com.marketplace.one1fi.feature.shop.marketplace.data.MarketplaceDataSource
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.ProductDto
import javax.inject.Inject

/**
 * Real backend-backed implementation. Not bound in [com.onefi.app.feature.shop.marketplace.di.MarketplaceModule]
 * yet — kept here so the swap from mock data to a live API is a one-line
 * DI change once the endpoint exists, with zero changes to the repository,
 * ViewModels or UI.
 */
class RemoteMarketplaceDataSource @Inject constructor(
    private val api: MarketplaceApiService
) : MarketplaceDataSource {

    override suspend fun fetchProducts(): List<ProductDto> =
        api.getProducts().products

    override suspend fun fetchProductDetail(productId: String): ProductDto =
        api.getProductDetail(productId)
}
