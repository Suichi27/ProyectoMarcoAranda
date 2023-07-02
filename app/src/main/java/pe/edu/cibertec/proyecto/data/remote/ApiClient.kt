package pe.edu.cibertec.proyecto.data.remote



import pe.edu.cibertec.proyecto.data.remote.service.MapaService
import pe.edu.cibertec.proyecto.data.remote.service.PersonajeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val API_BASE_URL ="https://ludicrous-elastic-turn.glitch.me/"

    private var personajeService : PersonajeService? = null
    private var mapaService:  MapaService?= null
    private var retrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }

    fun getPersonajeInterface(): PersonajeService {
        if (personajeService == null) {
            personajeService = getRetrofit().create(PersonajeService::class.java)
        }
        return personajeService as PersonajeService
    }


    fun getMapaInterface(): MapaService {
        if (mapaService == null) {
            mapaService = getRetrofit().create(MapaService::class.java)
        }
        return mapaService as MapaService
    }
}