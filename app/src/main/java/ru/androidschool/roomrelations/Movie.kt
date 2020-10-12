package ru.androidschool.roomrelations

import androidx.room.*

@Entity
data class Movie(
    @PrimaryKey
    val id: Long,
    val movieGenreId: Long,
    @ColumnInfo(name = "title")
    val title: String?
)

@Entity
data class Genre(
    @PrimaryKey(autoGenerate = true)
    val genreId: Long,
    val genreName: String
)


data class MovieAndGenre(
    @Embedded
    val genre: Genre,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "movieGenreId"
    )
    val movies: List<Movie>
)

