package com.marketplace.one1fi.feature.shop.marketplace.di

import com.marketplace.one1fi.feature.shop.marketplace.data.MarketplaceDataSource
import com.marketplace.one1fi.feature.shop.marketplace.data.mock.MockMarketplaceDataSource
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.MarketplaceApiService
import com.marketplace.one1fi.feature.shop.marketplace.data.repository.MarketplaceRepository
import com.marketplace.one1fi.feature.shop.marketplace.data.repository.MarketplaceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MarketplaceModule {

    /**
     * Only binding that needs to change to go live: swap [MockMarketplaceDataSource]
     * for [com.onefi.app.feature.shop.marketplace.data.remote.RemoteMarketplaceDataSource]
     * once the real Marketplace endpoint is available.
     */
    @Binds
    @Singleton
    abstract fun bindMarketplaceDataSource(
        mock: MockMarketplaceDataSource
    ): MarketplaceDataSource

    @Binds
    @Singleton
    abstract fun bindMarketplaceRepository(
        impl: MarketplaceRepositoryImpl
    ): MarketplaceRepository

    companion object {
        /**
         * Kept ready for when [MarketplaceDataSource] is bound to the remote
         * implementation. Point `baseUrl` at the real host — never at a value
         * read from anywhere user-controlled.
         */
        @Provides
        @Singleton
        fun provideMarketplaceApiService(): MarketplaceApiService =
            Retrofit.Builder()
                .baseUrl("https://api.onefi.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarketplaceApiService::class.java)
    }
}
