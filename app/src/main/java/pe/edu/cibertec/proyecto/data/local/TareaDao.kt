package pe.edu.cibertec.proyecto.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pe.edu.cibertec.proyecto.data.model.Tarea

@Dao
interface TareaDao {
    @Insert
    fun insert(tarea: Tarea)

    @Delete
    fun delete(tarea: Tarea)

    @Update
    fun update(tarea: Tarea)

    @Query("select * from tareas")
    fun getAll(): List<Tarea>
}