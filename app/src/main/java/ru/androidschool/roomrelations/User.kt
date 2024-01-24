package ru.androidschool.roomrelations

import androidx.room.*

@Entity
data class User(
    @PrimaryKey
    val userId: Long,
    @ColumnInfo(name = "title")
    val name: String?
)

@Entity
data class Apartment(
    @PrimaryKey(autoGenerate = true)
    val apartmentId: Long,
    val apartmentAdress: String,
    val ownerId: Long
)


data class UserWithApartment(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "ownerId"
    )
    val apartments: List<Apartment>
)

