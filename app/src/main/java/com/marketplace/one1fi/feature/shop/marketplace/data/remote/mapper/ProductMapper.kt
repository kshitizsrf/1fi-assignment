package com.marketplace.one1fi.feature.shop.marketplace.data.remote.mapper

import com.marketplace.one1fi.feature.shop.marketplace.data.model.EmiPlan
import com.marketplace.one1fi.feature.shop.marketplace.data.model.Product
import com.marketplace.one1fi.feature.shop.marketplace.data.model.ProductVariant
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.EmiPlanDto
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.ProductDto
import com.marketplace.one1fi.feature.shop.marketplace.data.remote.dto.ProductVariantDto

fun ProductDto.toDomain(): Product = Product(
    id = id,
    name = name,
    brand = brand,
    category = category,
    imageUrls = imageUrls,
    mrp = mrp,
    basePrice = basePrice,
    ratingOutOf5 = rating,
    startingEmiPerMonth = emiPlans.minOfOrNull { it.monthlyAmount } ?: 0.0,
    shortHighlight = shortHighlight,
    highlights = highlights,
    variants = variants.map { it.toDomain() },
    emiPlans = emiPlans.map { it.toDomain() }
)

fun ProductVariantDto.toDomain(): ProductVariant = ProductVariant(
    id = id,
    attributeName = attributeName,
    label = label,
    priceDelta = priceDelta,
    isInStock = inStock
)

fun EmiPlanDto.toDomain(): EmiPlan = EmiPlan(
    id = id,
    tenureMonths = tenureMonths,
    interestRatePercent = interestRatePercent,
    monthlyAmount = monthlyAmount,
    processingFee = processingFee,
    totalPayable = totalPayable,
    isZeroInterest = isZeroInterest,
    isMostPopular = isMostPopular
)
