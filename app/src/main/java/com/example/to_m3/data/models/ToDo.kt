package com.example.to_m3.data.models

import java.util.Date

data class ToDo(
    val id: String,
    var title: String,
    var description: String,
    var category: String,
    var isCompleted: Boolean,
    val creationDate: Date
)
