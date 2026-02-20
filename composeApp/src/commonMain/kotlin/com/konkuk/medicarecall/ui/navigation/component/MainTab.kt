package com.konkuk.medicarecall.ui.navigation.component

import androidx.compose.runtime.Composable
import com.konkuk.medicarecall.resources.Res
import com.konkuk.medicarecall.resources.*
import com.konkuk.medicarecall.resources.ic_home_selected
import com.konkuk.medicarecall.resources.ic_home_unselected
import com.konkuk.medicarecall.resources.ic_settings_selected
import com.konkuk.medicarecall.resources.ic_settings_unselected
import com.konkuk.medicarecall.resources.ic_statistics_selected
import com.konkuk.medicarecall.resources.ic_statistics_unselected
import com.konkuk.medicarecall.ui.navigation.MainTabRoute
import com.konkuk.medicarecall.ui.navigation.Route
import org.jetbrains.compose.resources.DrawableResource

enum class MainTab(
    val label: String,
    val route: MainTabRoute,
    val description: String,
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
) {
    HOME(
        label = "하루 요약",
        route = MainTabRoute.Home,
        description = "Home Icon",
        selectedIcon = Res.drawable.ic_home_selected,
        unselectedIcon = Res.drawable.ic_home_unselected,
    ),
    WEEKLY_STATISTICS(
        label = "주간 통계",
        route = MainTabRoute.WeeklyStatistics,
        description = "Statistics Icon",
        selectedIcon = Res.drawable.ic_statistics_selected,
        unselectedIcon = Res.drawable.ic_statistics_unselected,
    ),
    SETTINGS(
        label = "설정",
        route = MainTabRoute.Settings,
        description = "Settings Icon",
        selectedIcon = Res.drawable.ic_settings_selected,
        unselectedIcon = Res.drawable.ic_settings_unselected,
    ),
    ;

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return MainTab.entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return MainTab.entries.map { it.route }.any { predicate(it) }
        }
    }
}
