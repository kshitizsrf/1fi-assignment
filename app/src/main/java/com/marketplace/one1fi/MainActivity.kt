package com.marketplace.one1fi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.marketplace.one1fi.core.components.OneFiBottomNavBar
import com.marketplace.one1fi.core.theme.OneFiTheme
import com.marketplace.one1fi.feature.shop.ShopTab
import com.marketplace.one1fi.feature.shop.marketplace.navigation.MarketplaceSectionRoot
import com.marketplace.one1fi.feature.shop.nearbystores.NearbyStoresScreen
import com.marketplace.one1fi.feature.shop.topbrands.TopBrandsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneFiTheme {
                var selectedTab by remember { mutableStateOf(ShopTab.MARKETPLACE) }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            OneFiBottomNavBar(
                                selected = selectedTab,
                                onSelect = { selectedTab = it }
                            )
                        }
                    ) { paddingValues ->
                        val contentModifier = Modifier.padding(paddingValues)
                        when (selectedTab) {
                            ShopTab.MARKETPLACE -> MarketplaceSectionRoot(modifier = contentModifier)
                            ShopTab.TOP_BRANDS -> TopBrandsScreen(modifier = contentModifier)
                            ShopTab.NEARBY_STORES -> NearbyStoresScreen(modifier = contentModifier)
                        }
                    }
                }
            }
        }
    }
}
