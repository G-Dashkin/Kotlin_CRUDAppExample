package com.example.kotlin_crudappexample.crudApp3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscribers_data_table")
data class Subscriber (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id")
    var id : Int,

    @ColumnInfo(name = "subscriber_name")
    var name : String,

    @ColumnInfo(name = "subscriber_email")
    var email : String,

)