package com.example.playstorecopy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature.addapp.AddAppScreen
import com.example.feature.appdetail.AppDetailScreen
import com.example.feature.applist.AppListScreen

private object Routes {
    const val APP_LIST  = "app_list"
    const val ADD_APP   = "add_app"
    const val APP_DETAIL = "app_detail/{appId}"

    fun appDetail(appId: String) = "app_detail/$appId"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.APP_LIST
    ) {
        composable(Routes.APP_LIST) {
            AppListScreen(
                onNavigateToDetail = { appId ->
                    navController.navigate(Routes.appDetail(appId))
                },
                onNavigateToAddApp = {
                    navController.navigate(Routes.ADD_APP)
                }
            )
        }

        composable(Routes.ADD_APP) {
            AddAppScreen(
                onNavigateBack = { navController.popBackStack() },
                onAppSaved    = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.APP_DETAIL,
            arguments = listOf(
                navArgument("appId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId") ?: return@composable
            AppDetailScreen(
                appId = appId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}