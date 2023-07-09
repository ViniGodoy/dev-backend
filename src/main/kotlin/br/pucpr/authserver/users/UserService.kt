package br.pucpr.authserver.users

import org.springframework.stereotype.Service

@Service
class UserService(val repository: UserRepository) {
    fun insert(user: User) = repository.save(user)
    fun findAll() = repository.findAll()
    fun findByIdOrNull(id: Long) = repository.findByIdOrNull(id)
}