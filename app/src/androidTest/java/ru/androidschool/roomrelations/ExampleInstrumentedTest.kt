package ru.androidschool.roomrelations

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ManyToManyTest {
    @get:Rule

    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        MovieDatabase::class.java
    )
        .build()

    private val underTest = db.movieDao()

    @Test
    fun relations() {

        val horrorGenre = Genre(1, "Ужасы")
        val comedyGenre = Genre(2, "Комедии")

        val horrorMovie = Movie(1, "Очень страшный фильм")

        val comedyMovie = Movie(2, "Очень смешной фильм")

        val strangeMovie = Movie(3, "Очень смешной фильм и страшный фильм")

        assertEquals(true, underTest.getMoviesAndGenres().isEmpty())

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

        underTest.save(horrorGenre)
        underTest.save(comedyGenre)

        underTest.save(listOf(horrorMovie, comedyMovie,strangeMovie))

        underTest.saveJoins(listOf(joinOne, joinTwo,joinThree,joinFour))

        assertEquals(false, underTest.getMoviesAndGenres().isEmpty())

        val all = underTest.getMoviesAndGenres()

        assertThat(all, hasSize(equalTo(2)))
        assertThat(all[0].genre, equalTo(horrorGenre))
        assertThat(all[1].genre, equalTo(comedyGenre))

        val loaded = underTest.loadByGenreId(comedyGenre.genreId)

        assertThat(loaded.genre, equalTo(comedyGenre))
        assertThat(
            loaded.movies[0], equalTo(comedyMovie)
        )
    }
}