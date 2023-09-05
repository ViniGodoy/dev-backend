package br.pucpr.authserver.controller.requests

import br.pucpr.authserver.users.Stubs.userStub
import br.pucpr.authserver.users.controller.requests.CreateUserRequest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CreateUserRequestTest {
    @Test
    fun `toUser creates a new user based on the reuquest`() {
        with(userStub()) {
            val req = CreateUserRequest(email, password, name).toUser()
            req.id shouldBe null
            req.name shouldBe name
            req.password shouldBe password
            req.email shouldBe email
        }
    }
}