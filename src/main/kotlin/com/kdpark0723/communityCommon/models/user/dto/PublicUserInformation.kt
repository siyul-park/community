package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.user.User

class PublicUserInformation(user: User) {
    val identifier: String? = user.identifier
    val nickname: String? = user.nickname
    val email: String? = user.email
}