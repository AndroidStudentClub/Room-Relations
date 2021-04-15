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
        HomeDatabase::class.java
    )
        .build()

    private val underTest = db.homeDao()

    @Test
    fun relations() {

        val moscow = Apartment(1, "Москва", 55)
        val spb = Apartment(2, "Питер",56)
        val sochi = Apartment(3, "Сочи",55)

        val user1 = User(55,  "Вася")
        val user2 = User(56,  "Петя")

        assertEquals(true, underTest.getUserAndAparts().isEmpty())

        underTest.save(moscow)
        underTest.save(spb)
        underTest.save(sochi)

        underTest.save(listOf(user1,user2))

        assertEquals(false, underTest.getUserAndAparts().isEmpty())

        val all = underTest.getUserAndAparts()

        assertThat(all, hasSize(equalTo(2)))
        assertThat(all[0].apartments[0], equalTo(moscow))
        assertThat(all[1].apartments[0], equalTo(spb))
        assertThat(all[0].apartments[1], equalTo(sochi))

        val loaded = underTest.loadByUserId(moscow.ownerId)

//        assertThat(loaded.user, equalTo(moscow))
//        assertThat(
//            loaded.movies[0], equalTo(movieTwo)
//        )
    }
}