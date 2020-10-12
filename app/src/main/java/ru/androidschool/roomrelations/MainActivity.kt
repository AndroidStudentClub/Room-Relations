package ru.androidschool.roomrelations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = MovieDatabase.get(this).movieDao()
        db.deleteAll()
        db.deleteAllGenres()
        db.deleteAllJoins()

        val horrorGenre = Genre(1, "Ужасы")
        val comedyGenre = Genre(2, "Комедии")

        val horrorMovie = Movie(1, "Очень страшный фильм")

        val comedyMovie = Movie(2, "Очень смешной фильм")

        val strangeMovie = Movie(3, "Очень смешной фильм и страшный фильм")


        val joinOne = MovieAndGenreCrossRef(
            movieId = horrorMovie.id,
            genreId = horrorGenre.genreId
        )

        val joinTwo = MovieAndGenreCrossRef(
            movieId = comedyMovie.id,
            genreId = comedyGenre.genreId
        )

        val joinThree = MovieAndGenreCrossRef(
            movieId = strangeMovie.id,
            genreId = comedyGenre.genreId
        )

        val joinFour = MovieAndGenreCrossRef(
            movieId = strangeMovie.id,
            genreId = horrorGenre.genreId
        )

        db.save(horrorGenre)
        db.save(comedyGenre)

        db.save(listOf(horrorMovie, comedyMovie, strangeMovie))

        db.saveJoins(listOf(joinOne, joinTwo, joinThree, joinFour))
    }
}