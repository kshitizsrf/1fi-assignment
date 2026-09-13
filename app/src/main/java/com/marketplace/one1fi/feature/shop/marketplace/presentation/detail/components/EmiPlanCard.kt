package com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marketplace.one1fi.core.theme.CardShape
import com.marketplace.one1fi.core.theme.OneFiBorder
import com.marketplace.one1fi.core.theme.OneFiGreen
import com.marketplace.one1fi.core.theme.OneFiGreenLight
import com.marketplace.one1fi.core.theme.OneFiTextPrimary
import com.marketplace.one1fi.core.theme.OneFiTextSecondary
import com.marketplace.one1fi.core.util.asRupees
import com.marketplace.one1fi.feature.shop.marketplace.data.model.EmiPlan

@Composable
fun EmiPlanCard(
    plan: EmiPlan,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onSelect,
        modifier = modifier.fillMaxWidth(),
        shape = CardShape,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
            else MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = if (isSelected) 1.5.dp else 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else OneFiBorder
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
            )
            Column(modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${plan.tenureMonths} months",
                        style = MaterialTheme.typography.titleMedium,
                        color = OneFiTextPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    if (plan.isMostPopular) {
                        Text(
                            text = "RECOMMENDED",
                            style = MaterialTheme.typography.labelSmall,
                            color = OneFiGreen,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .background(OneFiGreenLight, RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(
                    text = if (plan.isZeroInterest) "0% interest" else "${plan.interestRatePercent}% interest",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (plan.isZeroInterest) OneFiGreen else OneFiTextSecondary
                )
                Text(
                    text = "Total payable ${plan.totalPayable.asRupees()}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OneFiTextSecondary
                )
            }
            Text(
                text = "${plan.monthlyAmount.asRupees()}/mo",
                style = MaterialTheme.typography.titleMedium,
                color = OneFiTextPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
