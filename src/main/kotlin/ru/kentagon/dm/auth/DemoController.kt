package ru.kentagon.dm.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/demo")
class DemoController {
    @GetMapping(path = ["/user"])
    fun testAuth(): ResponseEntity<String>{
        return ResponseEntity.ok("Hello, User")
    }

    @GetMapping(path = ["/moderator"])
    fun testModerator():ResponseEntity<String>{
        return ResponseEntity.ok("Hello, Moderator")
    }

    @GetMapping(path = ["/admin"])
    fun testAdmin(): ResponseEntity<String>{
        return ResponseEntity.ok("Hello, Admin!")
    }
}
