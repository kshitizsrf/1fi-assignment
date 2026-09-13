package com.marketplace.one1fi.feature.shop.marketplace.data.model

/**
 * One selectable configuration axis of a product, e.g. Storage: "256GB",
 * or Color: "Midnight". [attributeName] groups variants in the UI so
 * "256GB" and "Midnight" render as two separate chip rows, not one.
 */
data class ProductVariant(
    val id: String,
    val attributeName: String,
    val label: String,
    val priceDelta: Double,
    val isInStock: Boolean = true
)
