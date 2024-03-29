package com.kdpark0723.communityCommon.model.role.dataAccess

import com.kdpark0723.communityCommon.model.role.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : CrudRepository<Role, Long> {

    fun findByName(name: Role.Name): Optional<Role>

    fun existsByName(name: Role.Name): Boolean
}