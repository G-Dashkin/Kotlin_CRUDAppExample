package com.example.kotlin_crudappexample.crudApp2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var firstName: String = "",
    var lastName: String = "",
    var isChecked: Boolean = false,
): java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}