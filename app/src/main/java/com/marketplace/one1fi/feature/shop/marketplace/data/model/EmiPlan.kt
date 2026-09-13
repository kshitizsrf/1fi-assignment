package com.marketplace.one1fi.feature.shop.marketplace.data.model

/** One selectable EMI tenure/plan for a product, as shown on the product detail screen. */
data class EmiPlan(
    val id: String,
    val tenureMonths: Int,
    val interestRatePercent: Double,
    val monthlyAmount: Double,
    val processingFee: Double,
    val totalPayable: Double,
    val isZeroInterest: Boolean,
    val isMostPopular: Boolean = false
)
