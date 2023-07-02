package pe.edu.cibertec.proyecto.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.cibertec.proyecto.data.model.Tarea

@Database(entities = [Tarea::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun tareaDao(): TareaDao

    companion object{
        private var INSTANCE: AppDatabase?=null

        fun getInstance(context: Context): AppDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "agenda.db").allowMainThreadQueries().build()
            }
            return INSTANCE as AppDatabase
        }
    }
}