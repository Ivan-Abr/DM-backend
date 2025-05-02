package ru.kentagon.dm.auth.dto

data class ResponseDTO (
    var message: String,
    var token: String,
    val refreshToken: String
)