package com.konkuk.medicarecall.ui.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.konkuk.medicarecall.ui.feature.home.screen.HomeScreen
import com.konkuk.medicarecall.ui.feature.home.viewmodel.HomeViewModel
import com.konkuk.medicarecall.ui.navigation.MainTabRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    navigateToMealDetailScreen: (Long) -> Unit,
    navigateToMedicineDetailScreen: (Long) -> Unit,
    navigateToSleepDetailScreen: (Long) -> Unit,
    navigateToStateHealthDetailScreen: (Long) -> Unit,
    navigateToStateMentalDetailScreen: (Long) -> Unit,
    navigateToGlucoseDetailScreen: (Long) -> Unit,
    getBackStackHomeViewModel: @Composable (NavBackStackEntry) -> HomeViewModel,
) {
    composable<MainTabRoute.Home> { backStackEntry ->
        HomeScreen(
            viewModel = getBackStackHomeViewModel(backStackEntry),
            navigateToMealDetailScreen = { elderId ->
                navigateToMealDetailScreen(elderId)
            },
            navigateToMedicineDetailScreen = { elderId ->
                navigateToMedicineDetailScreen(elderId)
            },
            navigateToSleepDetailScreen = { elderId ->
                navigateToSleepDetailScreen(elderId)
            },
            navigateToStateHealthDetailScreen = { elderId ->
                navigateToStateHealthDetailScreen(elderId)
            },
            navigateToStateMentalDetailScreen = { elderId ->
                navigateToStateMentalDetailScreen(elderId)
            },
            navigateToGlucoseDetailScreen = { elderId ->
                navigateToGlucoseDetailScreen(elderId)
            },
        )
    }
}
