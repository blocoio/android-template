package io.bloco.template.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.bloco.template.features.details.DetailsScreen
import io.bloco.template.features.list.ListScreen

@Composable
fun TemplateNaveHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = NavRoutes.List.route) {
        composable(NavRoutes.List.navigationRoute()) {
            ListScreen(hiltViewModel(), openDetailsClicked = {
                navController.navigate(NavRoutes.Details.navigationRoute(it))
            })
        }
        composable(NavRoutes.Details.route) { backStackEntry ->
            backStackEntry.arguments?.getString(NavRoutes.Details.Id)?.let {
                DetailsScreen(detailViewModel(bookId = it))
            }
        }
    }
}