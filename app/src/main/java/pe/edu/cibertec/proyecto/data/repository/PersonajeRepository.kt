package pe.edu.cibertec.proyecto.data.repository

import pe.edu.cibertec.proyecto.data.model.Personaje
import pe.edu.cibertec.proyecto.data.remote.ApiClient
import pe.edu.cibertec.proyecto.data.remote.service.PersonajeService
import pe.edu.cibertec.proyecto.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonajeRepository(
    private val personajeService: PersonajeService = ApiClient.getPersonajeInterface()
) {
    fun getPersonajes(callback: (Result<List<Personaje>>) -> Unit) {

        personajeService.getPersonajes().enqueue(object : Callback<List<Personaje>> {
            override fun onResponse(
                call: Call<List<Personaje>>,
                response: Response<List<Personaje>>
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

            override fun onFailure(call: Call<List<Personaje>>, t: Throwable) {
                callback(Result.Error("Get Personaje not available"))
            }
        })
    }
}