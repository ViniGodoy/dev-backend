package br.pucpr.authserver.users

class User (
    var id: Long? = null,
    var email: String,
    var password: String,
    var name: String = "",
)