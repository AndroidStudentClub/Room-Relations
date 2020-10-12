package ru.androidschool.roomrelations

import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(genre: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movies: List<Movie>)

    @Transaction
    @Query("SELECT * FROM Genre")
    fun getMoviesAndGenres(): List<MovieAndGenre>

    @Transaction
    @Query("SELECT * FROM Genre WHERE genreId = :genreCode")
    fun loadByGenreId(genreCode: Long): MovieAndGenre

    @Query("DELETE FROM movie")
    fun deleteAll()
}

