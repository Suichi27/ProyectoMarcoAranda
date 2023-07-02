package pe.edu.cibertec.proyecto

sealed class Screen(val route: String){
    object Characters: Screen("PersonajeList")
    object Taskt: Screen("Tareas")
}
