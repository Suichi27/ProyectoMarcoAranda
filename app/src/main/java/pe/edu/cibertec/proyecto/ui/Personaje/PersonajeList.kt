package pe.edu.cibertec.proyecto.ui.Personaje


import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import okhttp3.Route
import pe.edu.cibertec.proyecto.Screen
import pe.edu.cibertec.proyecto.data.model.Mapa
import pe.edu.cibertec.proyecto.data.model.Personaje
import pe.edu.cibertec.proyecto.data.repository.MapaRepository
import pe.edu.cibertec.proyecto.data.repository.PersonajeRepository
import pe.edu.cibertec.proyecto.util.Result




@Composable
fun PersonajeList(navController: NavController){

    val personajes = remember{
        mutableStateOf(listOf<Personaje>())
    }

    val mapas = remember{
        mutableStateOf(listOf<Mapa>())
    }

    val scrollState = rememberScrollState()

    val personajeRepository = PersonajeRepository()
    val mapasRepository = MapaRepository()
    val context = LocalContext.current

    personajeRepository.getPersonajes { result ->
        if(result is Result.Success){
            personajes.value = result.data!!
        }else {
            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    mapasRepository.getMapas { result ->
        if(result is Result.Success){
            mapas.value = result.data!!
        }else {
            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }




    Column( modifier = Modifier
        .fillMaxHeight()
        .padding(horizontal = 16.dp)
        .verticalScroll(scrollState)) {

        Text(
            text = "Tareas Pendientes",
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.Taskt.route)

            }.padding(8.dp).fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Personajes Creados",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            textAlign = TextAlign.Center

        )

        LazyRow() {
            items(personajes.value) { personaje ->
                Box(modifier = Modifier.padding(8.dp)) {
                    Card {
                        AsyncImage(
                            model = personaje.img,
                            contentDescription = null,
                            modifier = Modifier
                                .height(500.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop

                        )
                    }
                    Box(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(8.dp)
                        )
                    ) {
                        Text(text = personaje.name, modifier = Modifier.padding(8.dp))
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                }
            }
        }

        Text(
            text = "Mapas Guardados",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            textAlign = TextAlign.Center

        )


        LazyRow() {
            items(mapas.value) { mapa ->
                Box(modifier = Modifier.padding(8.dp)) {
                    Card {
                        AsyncImage(
                            model = mapa.imgMap,
                            contentDescription = null,
                            modifier = Modifier
                                .height(400.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop

                        )
                    }
                    Box(
                        modifier = Modifier.background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(8.dp)
                        )
                    ) {
                        Text(text = mapa.name, modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }

}