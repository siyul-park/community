package com.kdpark0723.communityCommon.services.auth

import com.kdpark0723.communityCommon.exceptions.InvalidElementException
import com.kdpark0723.communityCommon.exceptions.UndefinedElementTypeException
import com.kdpark0723.communityCommon.exceptions.UserAlreadySignedException
import com.kdpark0723.communityCommon.models.user.User
import com.kdpark0723.communityCommon.models.user.createUser
import com.kdpark0723.communityCommon.models.user.dao.UserDAO
import com.kdpark0723.communityCommon.models.user.dto.SignInElement
import com.kdpark0723.communityCommon.models.user.dto.SignInResponse
import org.springframework.stereotype.Component
import javax.validation.Validation
import javax.validation.ValidatorFactory

@Component
class SignInService(private var userDAO: UserDAO) {

    private var factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
    private var validator = factory.validator

    private final val validIdentifier = "validId@community.com"
    private final val validHashedPassword = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259"

    val validUser: User = createUser(validIdentifier, validHashedPassword, validIdentifier, validIdentifier)

    fun checkValue(element: SignInElement) {
        val checkedUser: User = getCanInvalidUser(element)

        val violations = validator.validate(checkedUser)
        for (violation in violations) {
            throw InvalidElementException(violation.message)
        }
    }

    private fun getCanInvalidUser(element: SignInElement): User {
        return when (element.type) {
            "identifier" ->
                validUser.copy(identifier = element.value)
            "hashed_password" ->
                validUser.copy(hashedPassword = element.value)
            "nickname" ->
                validUser.copy(nickname = element.value)
            "email" ->
                validUser.copy(email = element.value)
            else ->
                throw UndefinedElementTypeException()
        }
    }

    fun signIn(user: User): SignInResponse {
        if (userDAO.exists(user.identifier))
            throw UserAlreadySignedException()

        userDAO.save(user)

        return SignInResponse(user)
    }
}