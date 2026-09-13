package com.marketplace.one1fi.feature.shop.marketplace.data.mock

import com.marketplace.one1fi.feature.shop.marketplace.data.MarketplaceDataSource
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.EmiPlanDto
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.ProductDto
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.ProductVariantDto
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.time.Duration.Companion.milliseconds

/**
 * Stands in for the Marketplace backend. This is the *only* place product/EMI
 * data is authored by hand — everything above it (repository, ViewModels,
 * Composables) only ever sees [ProductDto] / domain [com.onefi.app.feature.shop.marketplace.data.model.Product],
 * so pointing [com.onefi.app.feature.shop.marketplace.di.MarketplaceModule] at
 * [com.onefi.app.feature.shop.marketplace.data.remote.RemoteMarketplaceDataSource]
 * instead is the only change needed to go live.
 *
 * A small artificial delay simulates network latency so loading states are
 * actually exercised; flip [simulateError] to true to exercise the error state.
 */
class MockMarketplaceDataSource @Inject constructor() : MarketplaceDataSource {

    var simulateError: Boolean = false

    override suspend fun fetchProducts(): List<ProductDto> {
        delay(NETWORK_DELAY_MS.milliseconds)
        if (simulateError) error("Unable to reach Marketplace service")
        return catalog
    }

    override suspend fun fetchProductDetail(productId: String): ProductDto {
        delay(NETWORK_DELAY_MS.milliseconds)
        if (simulateError) error("Unable to reach Marketplace service")
        return catalog.firstOrNull { it.id == productId }
            ?: error("Product $productId not found")
    }

    companion object {
        private const val NETWORK_DELAY_MS = 600L

        private fun emiPlansFor(basePrice: Double): List<EmiPlanDto> = listOf(
            EmiPlanDto(
                id = "emi_3m",
                tenureMonths = 3,
                interestRatePercent = 0.0,
                monthlyAmount = ceil(basePrice / 3),
                processingFee = 0.0,
                totalPayable = basePrice,
                isZeroInterest = true,
                isMostPopular = false
            ),
            EmiPlanDto(
                id = "emi_6m",
                tenureMonths = 6,
                interestRatePercent = 0.0,
                monthlyAmount = ceil(basePrice / 6),
                processingFee = 0.0,
                totalPayable = basePrice,
                isZeroInterest = true,
                isMostPopular = true
            ),
            EmiPlanDto(
                id = "emi_12m",
                tenureMonths = 12,
                interestRatePercent = 12.0,
                monthlyAmount = ceil(basePrice * 1.065 / 12),
                processingFee = 499.0,
                totalPayable = ceil(basePrice * 1.065),
                isZeroInterest = false,
                isMostPopular = false
            ),
            EmiPlanDto(
                id = "emi_24m",
                tenureMonths = 24,
                interestRatePercent = 14.0,
                monthlyAmount = ceil(basePrice * 1.14 / 24),
                processingFee = 499.0,
                totalPayable = ceil(basePrice * 1.14),
                isZeroInterest = false,
                isMostPopular = false
            )
        )

        private val catalog: List<ProductDto> = listOf(
            ProductDto(
                id = "prod_macbook_air_m3",
                name = "MacBook Air (M3, 13-inch)",
                brand = "Apple",
                category = "Laptops",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1611186871348-b1ce696e52c9",
                    "https://images.unsplash.com/photo-1517336714731-489689fd1ca8"
                ),
                mrp = 119900.0,
                basePrice = 114900.0,
                rating = 4.8,
                shortHighlight = "Starts at ₹2,000/mo",
                highlights = listOf(
                    "Apple M3 chip with 8-core CPU",
                    "Up to 18 hours battery life",
                    "13.6-inch Liquid Retina display",
                    "1 year Apple warranty"
                ),
                variants = listOf(
                    ProductVariantDto("var_256gb", "Storage", "256GB", 0.0, true),
                    ProductVariantDto("var_512gb", "Storage", "512GB", 20000.0, true),
                    ProductVariantDto("var_color_silver", "Color", "Silver", 0.0, true),
                    ProductVariantDto("var_color_midnight", "Color", "Midnight", 0.0, true)
                ),
                emiPlans = emiPlansFor(114900.0)
            ),
            ProductDto(
                id = "prod_iphone_15",
                name = "iPhone 15",
                brand = "Apple",
                category = "Mobiles",
                imageUrls = listOf(
                    "https://commons.wikimedia.org/wiki/Special:FilePath/Back%20of%20iPhone%2015.jpg?width=800"
                ),
                mrp = 79900.0,
                basePrice = 74900.0,
                rating = 4.7,
                shortHighlight = "Starts at ₹1,300/mo",
                highlights = listOf(
                    "A16 Bionic chip",
                    "48MP main camera",
                    "Dynamic Island",
                    "USB-C connector"
                ),
                variants = listOf(
                    ProductVariantDto("var_128gb", "Storage", "128GB", 0.0, true),
                    ProductVariantDto("var_256gb_ip", "Storage", "256GB", 10000.0, true),
                    ProductVariantDto("var_color_black", "Color", "Black", 0.0, true),
                    ProductVariantDto("var_color_pink", "Color", "Pink", 0.0, true)
                ),
                emiPlans = emiPlansFor(74900.0)
            ),
            ProductDto(
                id = "prod_galaxy_s24",
                name = "Samsung Galaxy S24",
                brand = "Samsung",
                category = "Mobiles",
                imageUrls = listOf(
                    "https://commons.wikimedia.org/wiki/Special:FilePath/Samsung%20Galaxy%20S24.jpg?width=800"
                ),
                mrp = 74999.0,
                basePrice = 69999.0,
                rating = 4.5,
                shortHighlight = "Starts at ₹1,200/mo",
                highlights = listOf(
                    "Snapdragon 8 Gen 3",
                    "Galaxy AI features",
                    "120Hz Dynamic AMOLED display"
                ),
                variants = listOf(
                    ProductVariantDto("var_128gb_s24", "Storage", "128GB", 0.0, true),
                    ProductVariantDto("var_256gb_s24", "Storage", "256GB", 6000.0, true)
                ),
                emiPlans = emiPlansFor(69999.0)
            ),
            ProductDto(
                id = "prod_oneplus_12",
                name = "OnePlus 12",
                brand = "OnePlus",
                category = "Mobiles",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1598327105666-5b89351aff97"
                ),
                mrp = 69999.0,
                basePrice = 64999.0,
                rating = 4.6,
                shortHighlight = "Starts at ₹1,100/mo",
                highlights = listOf(
                    "Snapdragon 8 Gen 3",
                    "100W SUPERVOOC charging",
                    "Hasselblad camera system"
                ),
                variants = listOf(
                    ProductVariantDto("var_256gb_op", "Storage", "256GB", 0.0, true),
                    ProductVariantDto("var_512gb_op", "Storage", "512GB", 6000.0, false)
                ),
                emiPlans = emiPlansFor(64999.0)
            ),
            ProductDto(
                id = "prod_hp_pavilion",
                name = "HP Pavilion 15",
                brand = "HP",
                category = "Laptops",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1588872657578-7efd1f1555ed"
                ),
                mrp = 68999.0,
                basePrice = 62999.0,
                rating = 4.3,
                shortHighlight = "Starts at ₹1,050/mo",
                highlights = listOf(
                    "Intel Core i5 13th Gen",
                    "16GB RAM / 512GB SSD",
                    "15.6-inch FHD display"
                ),
                variants = listOf(
                    ProductVariantDto("var_i5", "Processor", "Core i5", 0.0, true),
                    ProductVariantDto("var_i7", "Processor", "Core i7", 12000.0, true)
                ),
                emiPlans = emiPlansFor(62999.0)
            ),
            ProductDto(
                id = "prod_asus_rog",
                name = "ASUS ROG Strix G16",
                brand = "ASUS",
                category = "Laptops",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1603302576837-37561b2e2302"
                ),
                mrp = 149990.0,
                basePrice = 139990.0,
                rating = 4.7,
                shortHighlight = "Starts at ₹2,300/mo",
                highlights = listOf(
                    "Intel Core i9 13th Gen",
                    "RTX 4070 8GB GDDR6",
                    "16-inch QHD 165Hz display"
                ),
                variants = listOf(
                    ProductVariantDto("var_rtx4060", "GPU", "RTX 4060", 0.0, true),
                    ProductVariantDto("var_rtx4070", "GPU", "RTX 4070", 15000.0, true)
                ),
                emiPlans = emiPlansFor(139990.0)
            )
        )
    }
}
