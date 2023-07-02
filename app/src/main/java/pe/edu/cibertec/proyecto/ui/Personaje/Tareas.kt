package pe.edu.cibertec.proyecto.ui.Personaje


import android.content.Intent
import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.cibertec.proyecto.Screen
import pe.edu.cibertec.proyecto.data.local.AppDatabase
import pe.edu.cibertec.proyecto.data.model.Tarea
import pe.edu.cibertec.proyecto.ui.theme.ProyectoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tareas(navController: NavController){

    val name= remember {
        mutableStateOf("")
    }

    val tareas = remember {
        mutableStateOf(listOf<Tarea>())
    }

    var editTarea = Tarea(0,"")

    val isEditing = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val tareaDao = AppDatabase.getInstance(context).tareaDao()

    tareas.value= tareaDao.getAll()



    Column( modifier = Modifier
        .fillMaxHeight()
        .padding(horizontal = 16.dp)
        ) {

        Text(
            text = "Personajes y Mapas",
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.Characters.route)

            }.padding(8.dp).fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center

        )


        Scaffold(


            topBar = {


                TopAppBar(title = {



                    Text(
                        text = "Agregar Tareas",

                        )
                },
                    actions = {
                        IconButton(onClick = {
                            if (isEditing.value) {
                                editTarea.name = name.value
                                tareaDao.update(editTarea)
                            } else {
                                val tarea = Tarea(0, name.value)
                                tareaDao.insert(tarea)
                            }


                            tareas.value = tareaDao.getAll()

                            name.value = ""
                            isEditing.value = false
                        }) {
                            Icon(
                                if (isEditing.value) {
                                    Icons.Filled.Edit
                                } else {
                                    Icons.Filled.Add
                                },
                                null,
                            )
                        }
                    }
                )

            }

        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name.value,
                    onValueChange = {
                        name.value = it
                    })

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(tareas.value) { tarea ->
                        Card(modifier = Modifier.fillMaxWidth(), onClick = {
                            name.value = tarea.name
                            editTarea = tarea
                            isEditing.value = true
                        }) {
                            Row {
                                Text(modifier = Modifier.weight(7f), text = tarea.name)
                                IconButton(modifier = Modifier.weight(1f), onClick = {

                                }) {

                                }
                                IconButton(modifier = Modifier.weight(1f), onClick = {
                                    tareaDao.delete(tarea)
                                    tareas.value = tareaDao.getAll()
                                }) {
                                    Icon(Icons.Filled.Delete, null)
                                }

                            }
                        }

                    }
                }
            }
        }

    }

}



