package com.marketplace.one1fi.feature.shop.marketplace.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.marketplace.one1fi.core.components.NetworkImage
import com.marketplace.one1fi.core.theme.CardShape
import com.marketplace.one1fi.core.theme.OneFiBorder
import com.marketplace.one1fi.core.theme.OneFiTextPrimary
import com.marketplace.one1fi.core.theme.OneFiTextSecondary
import com.marketplace.one1fi.core.util.asRupees
import com.marketplace.one1fi.feature.shop.marketplace.data.model.Product

/** Product tile for the Marketplace grid — image, name, price, and the EMI teaser badge. */
@Composable
fun ProductCard(
    product: Product,
    onClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick(product) },
        modifier = modifier.fillMaxWidth(),
        shape = CardShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, OneFiBorder)
    ) {
        Column {
            NetworkImage(
                url = product.imageUrls.firstOrNull().orEmpty(),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.1f)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = product.brand,
                    style = MaterialTheme.typography.labelSmall,
                    color = OneFiTextSecondary
                )
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = OneFiTextPrimary,
                    maxLines = 1
                )
                androidx.compose.foundation.layout.Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 6.dp)
                ) {
                    Text(
                        text = product.basePrice.asRupees(),
                        style = MaterialTheme.typography.titleMedium,
                        color = OneFiTextPrimary
                    )
                    if (product.mrp > product.basePrice) {
                        Text(
                            text = product.mrp.asRupees(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = OneFiTextSecondary,
                            textDecoration = TextDecoration.LineThrough,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                }
                Text(
                    text = product.shortHighlight,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
