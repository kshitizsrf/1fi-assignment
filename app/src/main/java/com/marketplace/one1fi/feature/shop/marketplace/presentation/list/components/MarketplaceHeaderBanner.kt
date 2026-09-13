package com.marketplace.one1fi.feature.shop.marketplace.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.marketplace.one1fi.core.theme.BannerShape
import com.marketplace.one1fi.core.theme.OneFiHeroGradient
import com.marketplace.one1fi.core.theme.OneFiTextOnPurple

/**
 * Purple hero banner at the top of the Marketplace tab — same treatment as
 * the "0% INTEREST" card on the Shop/Home screen, reused here to introduce
 * the section instead of inventing a new visual language.
 */
@Composable
fun MarketplaceHeaderBanner(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(OneFiHeroGradient, BannerShape)
            .padding(20.dp)
    ) {
        Text(
            text = "1Fi Marketplace",
            style = MaterialTheme.typography.headlineSmall,
            color = OneFiTextOnPurple,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Shop top brands with 0% interest EMI plans",
            style = MaterialTheme.typography.bodyMedium,
            color = OneFiTextOnPurple.copy(alpha = 0.9f),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
