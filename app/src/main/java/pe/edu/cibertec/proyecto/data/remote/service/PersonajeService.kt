package pe.edu.cibertec.proyecto.data.remote.service

import pe.edu.cibertec.proyecto.data.model.Personaje
import retrofit2.Call
import retrofit2.http.GET

interface PersonajeService {
    @GET("characters")
    fun getPersonajes(): Call<List<Personaje>>
}