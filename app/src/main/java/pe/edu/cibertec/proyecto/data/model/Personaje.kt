package pe.edu.cibertec.proyecto.data.model

import com.google.gson.annotations.SerializedName

data class Personaje(


    val id: Int,
    val name: String,
    @SerializedName("img")
    val img: String
)
