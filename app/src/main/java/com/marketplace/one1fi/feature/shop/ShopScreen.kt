package com.marketplace.one1fi.feature.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.marketplace.one1fi.feature.shop.marketplace.navigation.MarketplaceSectionRoot
import com.marketplace.one1fi.feature.shop.nearbystores.NearbyStoresScreen
import com.marketplace.one1fi.feature.shop.topbrands.TopBrandsScreen
import kotlinx.coroutines.launch

/**
 * Renders inside the existing Shop destination/route of the host app —
 * this composable does NOT own the bottom navigation bar or the app's
 * top-level Scaffold; it is what the Shop tab's content slot renders.
 */
@Composable
fun ShopScreen(modifier: Modifier = Modifier) {
    val tabs = ShopTab.entries
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.background(MaterialTheme.colorScheme.primary)
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = {
                        Text(
                            text = tab.title,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (tabs[page]) {
                ShopTab.TOP_BRANDS -> TopBrandsScreen()
                ShopTab.NEARBY_STORES -> NearbyStoresScreen()
                ShopTab.MARKETPLACE -> MarketplaceSectionRoot()
            }
        }
    }
}
