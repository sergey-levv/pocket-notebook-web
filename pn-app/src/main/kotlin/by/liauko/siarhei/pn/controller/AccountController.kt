package by.liauko.siarhei.pn.controller

import by.liauko.siarhei.pn.controller.exception.NotFoundException
import by.liauko.siarhei.pn.controller.exception.UsernameAlreadyExistsException
import by.liauko.siarhei.pn.dto.Credential
import by.liauko.siarhei.pn.dto.Password
import by.liauko.siarhei.pn.service.AccountService
import by.liauko.siarhei.pn.service.exception.AccountAlreadyExistsException
import by.liauko.siarhei.pn.service.exception.AccountNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/user")
class AccountController {

    @Autowired
    private lateinit var accountService: AccountService

    @PostMapping("/signup")
    fun signup(@RequestBody accountDetails: Credential): Long? {
        try {
            return accountService.createAccount(accountDetails)
        } catch (e: AccountAlreadyExistsException) {
            throw UsernameAlreadyExistsException(e)
        }
    }

    @PutMapping("/{id}/deactivate")
    fun deactivate(@PathVariable id: Long) {
        try {
            accountService.deactivateAccount(id)
        } catch (e: AccountNotFoundException) {
            throw NotFoundException(e)
        }
    }

    @PutMapping("/update/password")
    fun updatePassword(@RequestBody password: Password) {
        try {
            accountService.updatePassword(password)
        } catch (e: AccountNotFoundException) {
            throw NotFoundException(e)
        }
    }
}
