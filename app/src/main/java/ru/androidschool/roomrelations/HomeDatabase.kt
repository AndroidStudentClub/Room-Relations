package ru.androidschool.roomrelations

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Apartment::class], version = 1)
abstract class HomeDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao

    companion object {
        private var instance: HomeDatabase? = null

        @Synchronized
        fun get(context: Context): HomeDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    HomeDatabase::class.java, "HomeDatabase"
                ).build()
            }
            return instance!!
        }

    }
}