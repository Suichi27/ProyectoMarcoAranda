package pe.edu.cibertec.proyecto

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.proyecto.data.model.Tarea
import pe.edu.cibertec.proyecto.ui.Personaje.PersonajeList
import pe.edu.cibertec.proyecto.ui.Personaje.Tareas

import pe.edu.cibertec.proyecto.ui.theme.ProyectoTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoTheme {

                    navController = rememberNavController( )
                SetupNavGraph(navController = navController)

                }
            }
        }


    }

