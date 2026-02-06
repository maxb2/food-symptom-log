package com.foodsymptomlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.foodsymptomlog.ui.screens.AddMealScreen
import com.foodsymptomlog.ui.screens.AddMedicationScreen
import com.foodsymptomlog.ui.screens.AddOtherEntryScreen
import com.foodsymptomlog.ui.screens.AddSymptomScreen
import com.foodsymptomlog.ui.screens.CalendarScreen
import com.foodsymptomlog.ui.screens.HistoryScreen
import com.foodsymptomlog.ui.screens.HomeScreen
import com.foodsymptomlog.ui.screens.SettingsScreen
import com.foodsymptomlog.ui.theme.FoodSymptomLogTheme
import com.foodsymptomlog.viewmodel.LogViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodSymptomLogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: LogViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(
                                viewModel = viewModel,
                                onAddMeal = { navController.navigate("add_meal") },
                                onAddSymptom = { navController.navigate("add_symptom") },
                                onAddOther = { navController.navigate("add_other") },
                                onAddMedication = { navController.navigate("add_medication") },
                                onViewHistory = { navController.navigate("history") },
                                onViewCalendar = { navController.navigate("calendar") },
                                onViewSettings = { navController.navigate("settings") },
                                onEditMeal = { id -> navController.navigate("edit_meal/$id") },
                                onEditSymptom = { id -> navController.navigate("edit_symptom/$id") },
                                onEditOther = { id -> navController.navigate("edit_other/$id") },
                                onEditMedication = { id -> navController.navigate("edit_medication/$id") }
                            )
                        }
                        composable("add_meal") {
                            AddMealScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                        composable("add_symptom") {
                            AddSymptomScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                        composable("add_other") {
                            AddOtherEntryScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                        composable("add_medication") {
                            AddMedicationScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                        composable("settings") {
                            SettingsScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                        composable("calendar") {
                            CalendarScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() },
                                onEditMeal = { id -> navController.navigate("edit_meal/$id") },
                                onEditSymptom = { id -> navController.navigate("edit_symptom/$id") },
                                onEditOther = { id -> navController.navigate("edit_other/$id") },
                                onEditMedication = { id -> navController.navigate("edit_medication/$id") }
                            )
                        }
                        composable("history") {
                            HistoryScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() },
                                onEditMeal = { id -> navController.navigate("edit_meal/$id") },
                                onEditSymptom = { id -> navController.navigate("edit_symptom/$id") },
                                onEditOther = { id -> navController.navigate("edit_other/$id") },
                                onEditMedication = { id -> navController.navigate("edit_medication/$id") }
                            )
                        }
                        // Edit routes
                        composable(
                            route = "edit_meal/{mealId}",
                            arguments = listOf(navArgument("mealId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val mealId = backStackEntry.arguments?.getLong("mealId")
                            AddMealScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() },
                                editId = mealId
                            )
                        }
                        composable(
                            route = "edit_symptom/{symptomId}",
                            arguments = listOf(navArgument("symptomId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val symptomId = backStackEntry.arguments?.getLong("symptomId")
                            AddSymptomScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() },
                                editId = symptomId
                            )
                        }
                        composable(
                            route = "edit_other/{otherEntryId}",
                            arguments = listOf(navArgument("otherEntryId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val otherEntryId = backStackEntry.arguments?.getLong("otherEntryId")
                            AddOtherEntryScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() },
                                editId = otherEntryId
                            )
                        }
                        composable(
                            route = "edit_medication/{medicationId}",
                            arguments = listOf(navArgument("medicationId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val medicationId = backStackEntry.arguments?.getLong("medicationId")
                            AddMedicationScreen(
                                viewModel = viewModel,
                                onNavigateBack = { navController.popBackStack() },
                                editId = medicationId
                            )
                        }
                    }
                }
            }
        }
    }
}
