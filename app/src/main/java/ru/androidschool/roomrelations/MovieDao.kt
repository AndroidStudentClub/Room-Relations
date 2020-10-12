package ru.androidschool.roomrelations

import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    fun save(genre: Genre)

    @Insert
    fun save(movie: Movie)

    @Insert
    fun save(movies: List<Movie>)

    @Transaction
    @Query("SELECT * FROM Genre")
    fun getMoviesAndGenres(): List<MovieAndGenre>

    @Insert
   fun saveJoins(joins: List<MovieAndGenreCrossRef>)

    @Transaction
    @Query("SELECT * FROM Genre WHERE genreId = :genreCode")
    fun loadByGenreId(genreCode: Long): MovieAndGenre

    @Query("DELETE FROM moviesManyToMany")
    fun deleteAll()

    @Query("DELETE FROM genre")
    fun deleteAllGenres()

    @Query("DELETE FROM movieandgenrecrossref")
    fun deleteAllJoins()
}

