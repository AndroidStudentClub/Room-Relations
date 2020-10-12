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
class OneToManyTest {
    @get:Rule

    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        MovieDatabase::class.java
    )
        .build()

    private val underTest = db.movieDao()

    @Test
    fun relations() {

        val horror = Genre(1, "Ужасы")
        val comedy = Genre(2, "Комедии")

        val movieOne = Movie(1, horror.genreId, "Очень страшный фильм")
        val movieTwo = Movie(0, comedy.genreId, "Очень смешной фильм")

        assertEquals(true, underTest.getMoviesAndGenres().isEmpty())

        underTest.save(horror)
        underTest.save(comedy)

        underTest.save(listOf(movieOne, movieTwo))

        assertEquals(false, underTest.getMoviesAndGenres().isEmpty())

        val all = underTest.getMoviesAndGenres()

        assertThat(all, hasSize(equalTo(2)))
        assertThat(all[0].genre, equalTo(horror))
        assertThat(all[1].genre, equalTo(comedy))

        val loaded = underTest.loadByGenreId(comedy.genreId)

        assertThat(loaded.genre, equalTo(comedy))
        assertThat(
            loaded.movies[0], equalTo(movieTwo)
        )
    }
}