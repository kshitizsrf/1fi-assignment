package com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marketplace.one1fi.core.theme.OneFiTextPrimary
import com.marketplace.one1fi.feature.shop.marketplace.data.model.ProductVariant

/**
 * Renders one chip row per variant attribute (e.g. "Storage", then "Color"),
 * so a product with multiple configuration axes doesn't collapse into one
 * confusing row.
 */
@Composable
fun VariantSelector(
    variants: List<ProductVariant>,
    selectedVariantIds: Map<String, String>,
    onVariantSelected: (attributeName: String, variantId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val groups = variants.groupBy { it.attributeName }

    Column(modifier = modifier.fillMaxWidth()) {
        groups.forEach { (attributeName, options) ->
            Text(
                text = attributeName,
                style = MaterialTheme.typography.titleMedium,
                color = OneFiTextPrimary,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(
                    8.dp
                )
            ) {
                items(options) { variant ->
                    val selected = selectedVariantIds[attributeName] == variant.id
                    FilterChip(
                        selected = selected,
                        enabled = variant.isInStock,
                        onClick = { onVariantSelected(attributeName, variant.id) },
                        label = {
                            Text(if (variant.isInStock) variant.label else "${variant.label} (Out of stock)")
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        }
    }
}
