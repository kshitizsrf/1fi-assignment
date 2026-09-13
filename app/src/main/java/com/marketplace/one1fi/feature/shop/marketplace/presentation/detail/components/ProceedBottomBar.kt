package com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marketplace.one1fi.core.components.OneFiPrimaryButton
import com.marketplace.one1fi.core.theme.OneFiTextPrimary
import com.marketplace.one1fi.core.theme.OneFiTextSecondary
import com.marketplace.one1fi.core.util.asRupees
import com.marketplace.one1fi.feature.shop.marketplace.data.model.EmiPlan

/** Fixed CTA bar at the bottom of the product detail screen. */
@Composable
fun ProceedBottomBar(
    selectedPlan: EmiPlan?,
    isEnabled: Boolean,
    onProceedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Pay monthly",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OneFiTextSecondary
                )
                Text(
                    text = selectedPlan?.let { "${it.monthlyAmount.asRupees()}/mo" }
                        ?: "Select a plan",
                    style = MaterialTheme.typography.titleLarge,
                    color = OneFiTextPrimary
                )
            }
            OneFiPrimaryButton(
                text = "Proceed",
                onClick = onProceedClick,
                enabled = isEnabled
            )
        }
    }
}
