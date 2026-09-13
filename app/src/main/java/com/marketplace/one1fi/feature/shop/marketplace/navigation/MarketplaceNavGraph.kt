package com.marketplace.one1fi.feature.shop.marketplace.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marketplace.one1fi.feature.shop.marketplace.presentation.detail.ProductDetailScreen
import com.marketplace.one1fi.feature.shop.marketplace.presentation.list.MarketplaceListScreen

object MarketplaceDestinations {
    const val PRODUCT_ID_ARG = "productId"
    const val LIST_ROUTE = "marketplace_list"
    const val DETAIL_ROUTE = "marketplace_detail/{$PRODUCT_ID_ARG}"

    fun detailRoute(productId: String) = "marketplace_detail/$productId"
}

/**
 * Self-contained nav host for the "1Fi Marketplace" tab. Scoped to this tab
 * only, so switching between "Top Brands" / "Nearby Stores" / "1Fi Marketplace"
 * on the Shop page never resets where the user is inside Marketplace, and vice
 * versa.
 */
@Composable
fun MarketplaceSectionRoot(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MarketplaceDestinations.LIST_ROUTE,
        modifier = modifier
    ) {
        composable(MarketplaceDestinations.LIST_ROUTE) {
            MarketplaceListScreen(
                onProductClick = { product ->
                    navController.navigate(MarketplaceDestinations.detailRoute(product.id))
                }
            )
        }
        composable(
            route = MarketplaceDestinations.DETAIL_ROUTE,
            arguments = listOf(navArgument(MarketplaceDestinations.PRODUCT_ID_ARG) {
                type = NavType.StringType
            })
        ) {
            ProductDetailScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
