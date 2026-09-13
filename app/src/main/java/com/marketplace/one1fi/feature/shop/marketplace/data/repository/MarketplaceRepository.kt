package com.marketplace.one1fi.feature.shop.marketplace.data.repository

import com.marketplace.one1fi.core.util.Resource
import com.marketplace.one1fi.feature.shop.marketplace.data.model.Product
import kotlinx.coroutines.flow.Flow

interface MarketplaceRepository {
    fun getProducts(): Flow<Resource<List<Product>>>
    fun getProductDetail(productId: String): Flow<Resource<Product>>
}
