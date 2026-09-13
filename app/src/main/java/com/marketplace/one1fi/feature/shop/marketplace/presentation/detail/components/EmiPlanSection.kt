package com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marketplace.one1fi.core.theme.OneFiTextPrimary
import com.marketplace.one1fi.feature.shop.marketplace.data.model.EmiPlan

@Composable
fun EmiPlanSection(
    plans: List<EmiPlan>,
    selectedPlanId: String?,
    onPlanSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Choose an EMI plan",
            style = MaterialTheme.typography.titleMedium,
            color = OneFiTextPrimary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            plans.forEach { plan ->
                EmiPlanCard(
                    plan = plan,
                    isSelected = plan.id == selectedPlanId,
                    onSelect = { onPlanSelected(plan.id) }
                )
            }
        }
    }
}
