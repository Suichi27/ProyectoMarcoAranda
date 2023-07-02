package pe.edu.cibertec.proyecto

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pe.edu.cibertec.proyecto.ui.Personaje.PersonajeList
import pe.edu.cibertec.proyecto.ui.Personaje.Tareas

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController ,
            startDestination = Screen.Characters.route
            ){
            composable(
                route = Screen.Characters.route
            ){
                PersonajeList(navController = navController)
            }
        composable(
            route = Screen.Taskt.route
        ){
            Tareas(navController = navController)
        }
    }
    
}