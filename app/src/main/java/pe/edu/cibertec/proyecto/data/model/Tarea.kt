package pe.edu.cibertec.proyecto.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas")
data class Tarea (
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name="name")
    var name: String

 )
