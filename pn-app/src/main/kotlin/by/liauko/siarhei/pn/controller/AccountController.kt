package by.liauko.siarhei.pn.controller

import by.liauko.siarhei.pn.controller.exception.IncorrectCredentialsException
import by.liauko.siarhei.pn.controller.exception.UsernameAlreadyExistsException
import by.liauko.siarhei.pn.dto.Account
import by.liauko.siarhei.pn.dto.Credential
import by.liauko.siarhei.pn.dto.NewPassword
import by.liauko.siarhei.pn.service.AccountService
import by.liauko.siarhei.pn.service.exception.AccountAlreadyExistsException
import by.liauko.siarhei.pn.service.exception.CredentialsVerificationException
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
    fun signup(@RequestBody accountDetails: Credential): Long? {
        try {
            return accountService.createAccount(accountDetails)
        } catch (e: AccountAlreadyExistsException) {
            throw UsernameAlreadyExistsException(e)
        }
    }

    @PostMapping("/deactivate")
    fun deactivate(@RequestBody account: Account) {
        try {
            accountService.deactivateAccount(account)
        } catch (e: CredentialsVerificationException) {
            throw IncorrectCredentialsException(e)
        }
    }

    @PostMapping("/update/password")
    fun updatePassword(@RequestBody newPassword: NewPassword) {
        try {
            accountService.updatePassword(newPassword)
        } catch (e: CredentialsVerificationException) {
            throw IncorrectCredentialsException(e)
        }
    }
}
