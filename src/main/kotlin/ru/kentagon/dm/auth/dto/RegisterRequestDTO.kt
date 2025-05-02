package ru.kentagon.dm.auth.dto

data class RegisterRequestDTO(
    var username: String,
    var email: String,
    var password: String,
)
