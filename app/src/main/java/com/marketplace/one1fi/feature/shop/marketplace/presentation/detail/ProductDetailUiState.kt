package com.marketplace.one1fi.feature.shop.marketplace.presentation.detail

import com.marketplace.one1fi.feature.shop.marketplace.data.model.EmiPlan
import com.marketplace.one1fi.feature.shop.marketplace.data.model.Product
import com.marketplace.one1fi.feature.shop.marketplace.data.model.ProductVariant

data class ProductDetailUiState(
    val isLoading: Boolean = true,
    val product: Product? = null,
    val selectedVariantIds: Map<String, String> = emptyMap(), // attributeName -> variantId
    val selectedEmiPlanId: String? = null,
    val errorMessage: String? = null
) {
    val selectedVariants: List<ProductVariant>
        get() = product?.variants.orEmpty().filter { selectedVariantIds[it.attributeName] == it.id }

    val selectedEmiPlan: EmiPlan?
        get() = product?.emiPlans.orEmpty().firstOrNull { it.id == selectedEmiPlanId }

    val effectivePrice: Double
        get() = (product?.basePrice ?: 0.0) + selectedVariants.sumOf { it.priceDelta }

    val isProceedEnabled: Boolean
        get() = product != null && selectedEmiPlanId != null
}
