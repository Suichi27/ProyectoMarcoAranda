package pe.edu.cibertec.proyecto.data.model

import com.google.gson.annotations.SerializedName

data class Mapa(

    val id: Int,
    val name: String,
    @SerializedName("imgMap")
    val imgMap: String
)
