package by.liauko.siarhei.pn.controller

import by.liauko.siarhei.pn.controller.exception.UsernameAlreadyExistsException
import by.liauko.siarhei.pn.dto.Account
import by.liauko.siarhei.pn.service.AccountService
import by.liauko.siarhei.pn.service.exception.AccountAlreadyExistsException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/user")
class AccountController {

    @Autowired
    private lateinit var accountService: AccountService

    @PostMapping("/signup")
    fun signup(@RequestBody accountDetails: Account): Long? {
        try {
            return accountService.createAccount(accountDetails)
        } catch (e: AccountAlreadyExistsException) {
            throw UsernameAlreadyExistsException(e)
        }
    }
}
