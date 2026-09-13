package com.marketplace.one1fi.feature.shop.marketplace.data.repository

import com.marketplace.one1fi.core.util.Resource
import com.marketplace.one1fi.feature.shop.marketplace.data.MarketplaceDataSource
import com.marketplace.one1fi.feature.shop.marketplace.data.model.Product
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.mapper.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MarketplaceRepositoryImpl @Inject constructor(
    private val dataSource: MarketplaceDataSource
) : MarketplaceRepository {

    override fun getProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading)
        val products = dataSource.fetchProducts().map { it.toDomain() }
        emit(Resource.Success(products))
    }.catch { throwable ->
        emit(
            Resource.Error(
                throwable.message ?: "Something went wrong. Please try again.",
                throwable
            )
        )
    }.flowOn(Dispatchers.IO)

    override fun getProductDetail(productId: String): Flow<Resource<Product>> = flow {
        emit(Resource.Loading)
        val product = dataSource.fetchProductDetail(productId).toDomain()
        emit(Resource.Success(product))
    }.catch { throwable ->
        emit(
            Resource.Error(
                throwable.message ?: "Something went wrong. Please try again.",
                throwable
            )
        )
    }.flowOn(Dispatchers.IO)
}
