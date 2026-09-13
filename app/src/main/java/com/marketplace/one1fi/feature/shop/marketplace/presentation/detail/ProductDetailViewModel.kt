package com.marketplace.one1fi.feature.shop.marketplace.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marketplace.one1fi.core.util.Resource
import com.marketplace.one1fi.feature.shop.marketplace.data.repository.MarketplaceRepository
import com.marketplace.one1fi.feature.shop.marketplace.navigation.MarketplaceDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** One-shot events the screen reacts to but shouldn't re-render on config change. */
sealed class ProductDetailEvent {
    data class ProceedRequested(val summary: String) : ProductDetailEvent()
}

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MarketplaceRepository
) : ViewModel() {


    private val productId: String = checkNotNull(savedStateHandle[MarketplaceDestinations.PRODUCT_ID_ARG])

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<ProductDetailEvent>()
    val events: SharedFlow<ProductDetailEvent> = _events

    init {
        loadProduct()
    }

    fun retry() = loadProduct()

    fun onVariantSelected(attributeName: String, variantId: String) {
        _uiState.value = _uiState.value.copy(
            selectedVariantIds = _uiState.value.selectedVariantIds + (attributeName to variantId)
        )
    }

    fun onEmiPlanSelected(emiPlanId: String) {
        _uiState.value = _uiState.value.copy(selectedEmiPlanId = emiPlanId)
    }

    fun onProceedClicked() {
        val state = _uiState.value
        val plan = state.selectedEmiPlan ?: return
        val summary = "${plan.tenureMonths} months • ₹${plan.monthlyAmount.toInt()}/mo selected for ${state.product?.name}"
        viewModelScope.launch {
            _events.emit(ProductDetailEvent.ProceedRequested(summary))
        }
    }

    private fun loadProduct() {
        viewModelScope.launch {
            repository.getProductDetail(productId).collect { resource ->
                _uiState.value = when (resource) {
                    is Resource.Loading -> _uiState.value.copy(isLoading = true, errorMessage = null)
                    is Resource.Error -> _uiState.value.copy(isLoading = false, errorMessage = resource.message)
                    is Resource.Success -> {
                        val product = resource.data
                        val defaultVariants = product.variants
                            .groupBy { it.attributeName }
                            .mapValues { (_, variants) -> variants.first { it.isInStock }.id }
                        val defaultEmiPlan = product.emiPlans.firstOrNull { it.isMostPopular }
                            ?: product.emiPlans.firstOrNull()
                        _uiState.value.copy(
                            isLoading = false,
                            product = product,
                            selectedVariantIds = defaultVariants,
                            selectedEmiPlanId = defaultEmiPlan?.id,
                            errorMessage = null
                        )
                    }
                }
            }
        }
    }
}
