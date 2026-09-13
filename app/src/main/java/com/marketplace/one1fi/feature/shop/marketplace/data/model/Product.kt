package com.marketplace.one1fi.feature.shop.marketplace.data.model

/**
 * Domain model consumed by the UI layer. Never expose DTOs directly to
 * Composables — they map from [com.onefi.app.feature.shop.marketplace.data.remote.dto.ProductDto]
 * (or from the mock source) via ProductMapper so the UI is insulated from
 * whatever the actual API/mock shape looks like.
 */
data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val category: String,
    val imageUrls: List<String>,
    val mrp: Double,
    val basePrice: Double,
    val ratingOutOf5: Double?,
    val startingEmiPerMonth: Double,
    val shortHighlight: String,
    val highlights: List<String>,
    val variants: List<ProductVariant>,
    val emiPlans: List<EmiPlan>
)
