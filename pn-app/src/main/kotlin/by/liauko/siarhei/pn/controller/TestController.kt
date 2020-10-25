package by.liauko.siarhei.pn.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @RequestMapping("/")
    fun index() = "It's working."
}
