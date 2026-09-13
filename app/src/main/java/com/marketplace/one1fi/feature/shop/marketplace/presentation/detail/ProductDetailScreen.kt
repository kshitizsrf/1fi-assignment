package com.marketplace.one1fi.feature.shop.marketplace.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.marketplace.one1fi.core.components.ErrorState
import com.marketplace.one1fi.core.components.LoadingState
import com.marketplace.one1fi.core.theme.OneFiTextPrimary
import com.marketplace.one1fi.core.theme.OneFiTextSecondary
import com.marketplace.one1fi.core.util.asRupees
import com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components.EmiPlanSection
import com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components.ProceedBottomBar
import com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components.ProductImagePager
import com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components.VariantSelector
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is ProductDetailEvent.ProceedRequested ->
                    snackbarHostState.showSnackbar(event.summary)
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(uiState.product?.name.orEmpty()) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (uiState.product != null) {
                ProceedBottomBar(
                    selectedPlan = uiState.selectedEmiPlan,
                    isEnabled = uiState.isProceedEnabled,
                    onProceedClick = viewModel::onProceedClicked
                )
            }
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> LoadingState(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )

            uiState.errorMessage != null -> ErrorState(
                message = uiState.errorMessage.orEmpty(),
                onRetry = viewModel::retry,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )

            uiState.product != null -> ProductDetailContent(
                uiState = uiState,
                onVariantSelected = viewModel::onVariantSelected,
                onEmiPlanSelected = viewModel::onEmiPlanSelected,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun ProductDetailContent(
    uiState: ProductDetailUiState,
    onVariantSelected: (String, String) -> Unit,
    onEmiPlanSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val product = uiState.product ?: return

    LazyColumn(modifier = modifier.fillMaxSize()) {
        item { ProductImagePager(imageUrls = product.imageUrls) }

        item {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = product.brand,
                    style = MaterialTheme.typography.labelSmall,
                    color = OneFiTextSecondary
                )
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = OneFiTextPrimary
                )
                Text(
                    text = uiState.effectivePrice.asRupees(),
                    style = MaterialTheme.typography.titleLarge,
                    color = OneFiTextPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )

                VariantSelector(
                    variants = product.variants,
                    selectedVariantIds = uiState.selectedVariantIds,
                    onVariantSelected = onVariantSelected
                )

                Text(
                    text = "Highlights",
                    style = MaterialTheme.typography.titleMedium,
                    color = OneFiTextPrimary,
                    modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    product.highlights.forEach { highlight ->
                        Text(
                            text = "• $highlight",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OneFiTextSecondary
                        )
                    }
                }

                EmiPlanSection(
                    plans = product.emiPlans,
                    selectedPlanId = uiState.selectedEmiPlanId,
                    onPlanSelected = onEmiPlanSelected,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}
