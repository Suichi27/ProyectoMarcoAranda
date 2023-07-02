package pe.edu.cibertec.proyecto.data.remote.service

import pe.edu.cibertec.proyecto.data.model.Mapa
import retrofit2.Call
import retrofit2.http.GET

interface MapaService {

    @GET("mapas")
    fun getMapas(): Call<List<Mapa>>
}