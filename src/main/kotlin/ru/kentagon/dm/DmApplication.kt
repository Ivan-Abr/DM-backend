package ru.kentagon.dm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DmApplication

fun main(args: Array<String>) {
	runApplication<DmApplication>(*args)
}
