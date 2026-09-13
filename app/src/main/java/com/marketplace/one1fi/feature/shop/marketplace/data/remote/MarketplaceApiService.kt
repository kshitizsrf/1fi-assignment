package com.marketplace.one1fi.feature.shop.marketplace.data.remote

import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.ProductDto
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Real backend contract for the Marketplace. Not wired up yet — [MarketplaceModule]
 * currently binds [com.onefi.app.feature.shop.marketplace.data.mock.MockMarketplaceDataSource]
 * as the active [com.onefi.app.feature.shop.marketplace.data.MarketplaceDataSource].
 * Once an endpoint exists, point Retrofit's baseUrl at it and flip that binding
 * to [com.onefi.app.feature.shop.marketplace.data.remote.RemoteMarketplaceDataSource] —
 * nothing above the data source layer needs to change.
 */
interface MarketplaceApiService {

    @GET("marketplace/products")
    suspend fun getProducts(): ProductListResponse

    @GET("marketplace/products/{id}")
    suspend fun getProductDetail(@Path("id") productId: String): ProductDto
}
