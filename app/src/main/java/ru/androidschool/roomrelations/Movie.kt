package ru.androidschool.roomrelations

import androidx.room.*

@Entity(tableName = "moviesManyToMany")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String?
)

@Entity
data class Genre(
    @PrimaryKey
    val genreId: Long,
    val genreName: String
)

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieAndGenreCrossRef(
    val movieId: Long,
    val genreId: Long
)

data class MovieAndGenre(
    @Embedded
    val genre: Genre,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "movieId",
        associateBy = Junction(
            MovieAndGenreCrossRef::class,
            parentColumn = "genreId",
            entityColumn = "movieId"
        )
    )
    val movies: List<Movie>
)

