package com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Wire format. Field names mirror what a real `/marketplace/products` endpoint
 * would return. Both the mock data source and the (future) Retrofit data
 * source produce these, so swapping one for the other never touches the
 * repository, ViewModel or UI.
 */
data class ProductDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("brand") val brand: String,
    @SerializedName("category") val category: String,
    @SerializedName("image_urls") val imageUrls: List<String>,
    @SerializedName("mrp") val mrp: Double,
    @SerializedName("base_price") val basePrice: Double,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("short_highlight") val shortHighlight: String,
    @SerializedName("highlights") val highlights: List<String>,
    @SerializedName("variants") val variants: List<ProductVariantDto>,
    @SerializedName("emi_plans") val emiPlans: List<EmiPlanDto>
)

data class ProductVariantDto(
    @SerializedName("id") val id: String,
    @SerializedName("attribute_name") val attributeName: String,
    @SerializedName("label") val label: String,
    @SerializedName("price_delta") val priceDelta: Double,
    @SerializedName("in_stock") val inStock: Boolean
)

data class EmiPlanDto(
    @SerializedName("id") val id: String,
    @SerializedName("tenure_months") val tenureMonths: Int,
    @SerializedName("interest_rate_percent") val interestRatePercent: Double,
    @SerializedName("monthly_amount") val monthlyAmount: Double,
    @SerializedName("processing_fee") val processingFee: Double,
    @SerializedName("total_payable") val totalPayable: Double,
    @SerializedName("is_zero_interest") val isZeroInterest: Boolean,
    @SerializedName("is_most_popular") val isMostPopular: Boolean
)

data class ProductListResponse(
    @SerializedName("products") val products: List<ProductDto>
)
