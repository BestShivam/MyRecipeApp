package eu.tutorials.myrecipeapp

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen (modifier = Modifier,
                viewstate = viewstate){
                // passing Object
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            }
        }
        composable(route = Screen.DetailScreen.route){
             // get value from recipe screen
            val category = navController.previousBackStackEntry?.savedStateHandle?.
                    get<Category>("cat") ?: Category("","",
                "","")
                CategoryDetailScreen(category = category)
        }
    }
}