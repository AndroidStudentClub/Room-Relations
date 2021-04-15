package ru.androidschool.roomrelations

import androidx.room.*

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(genre: Apartment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movies: List<User>)

    @Transaction
    @Query("SELECT * FROM User")
    fun getUserAndAparts(): List<UserWithApartment>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithAparts():List<UserWithApartment>

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    fun loadByUserId(userId: Long): UserWithApartment

    @Query("DELETE FROM User")
    fun deleteAll()
}

