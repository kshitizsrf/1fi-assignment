package com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marketplace.one1fi.core.components.NetworkImage

/** Swipeable product gallery with dot indicators — same pattern as the "OFFERS" carousel on Shop/Home. */
@Composable
fun ProductImagePager(imageUrls: List<String>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size.coerceAtLeast(1) })

    Box(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(state = pagerState) { page ->
            NetworkImage(
                url = imageUrls.getOrNull(page).orEmpty(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        if (imageUrls.size > 1) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(6.dp)
            ) {
                repeat(imageUrls.size) { index ->
                    val active = index == pagerState.currentPage
                    Box(
                        modifier = Modifier
                            .size(if (active) 8.dp else 6.dp)
                            .background(
                                color = if (active) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.outline,
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}
