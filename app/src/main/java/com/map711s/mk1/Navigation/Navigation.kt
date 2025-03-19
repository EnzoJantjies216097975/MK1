package com.map711s.mk1.Navigation

import androidx.compose.runtime.Composable
import com.map711s.mk1.ui.screens.CharacterDetailScreen
import com.map711s.mk1.ui.screens.HomeScreen
import com.map711s.mk1.ui.screens.KameoDetailScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MortalKombatNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Home screen with character and kameo listings
        composable("home") {
            HomeScreen(navController = navController)
        }

        // Character detail screen with moves, combos, and fatalities
        composable(
            route = "character/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
            CharacterDetailScreen(
                navController = navController,
                characterId = characterId
            )
        }

        // Kameo character detail screen
        composable(
            route = "kameo/{kameoId}",
            arguments = listOf(
                navArgument("kameoId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val kameoId = backStackEntry.arguments?.getString("kameoId") ?: ""
            KameoDetailScreen(
                navController = navController,
                kameoId = kameoId
            )
        }
    }
}
