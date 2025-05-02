package ru.kentagon.dm.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.servers.Server


@OpenAPIDefinition(
    info = Info(
        description = "OpenApi documentation for Speedrun API",
        title = "Speedrun.ru",
        version = "1.0",
    ),
    servers = [
        Server(
            description = "Local Env",
            url = "http://localhost:8080"
        )
    ],
    security = [
        SecurityRequirement(
            name = "bearerAuth"
        )
    ]
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    `in` = SecuritySchemeIn.HEADER
)
class OpenApiConfig