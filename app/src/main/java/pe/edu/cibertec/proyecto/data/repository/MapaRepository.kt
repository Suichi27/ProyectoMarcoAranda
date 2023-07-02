package pe.edu.cibertec.proyecto.data.repository

import pe.edu.cibertec.proyecto.data.model.Mapa
import pe.edu.cibertec.proyecto.data.remote.ApiClient
import pe.edu.cibertec.proyecto.data.remote.service.MapaService
import pe.edu.cibertec.proyecto.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapaRepository (
    private val MapaService: MapaService = ApiClient.getMapaInterface()
){
    fun getMapas(callback: (Result<List<Mapa>>) -> Unit) {

        MapaService.getMapas().enqueue(object : Callback<List<Mapa>> {
            override fun onResponse(
                call: Call<List<Mapa>>,
                response: Response<List<Mapa>>
            ) {
                if (!response.isSuccessful) {
                    callback(Result.Error("Unsuccessful response"))
                    return
                }

                if (response.body() == null) {
                    callback(Result.Error("No data found"))
                    return
                }
                callback(Result.Success(response.body()!!))
            }

            override fun onFailure(call: Call<List<Mapa>>, t: Throwable) {
                callback(Result.Error("Get albumes not available"))
            }
        })
    }
}