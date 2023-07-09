package br.pucpr.authserver.users

import br.pucpr.authserver.exception.BadRequestException
import br.pucpr.authserver.users.Stubs.userStub
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.mockk.checkUnnecessaryStub
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class UserServiceTest {
    private val repositoryMock = mockk<UserRepository>()
    private val service = UserService(repositoryMock)

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @AfterEach
    fun cleanUp() {
        checkUnnecessaryStub(repositoryMock)
    }

    @Test
    fun `Insert must throw BadRequestException if an user with the same email is found`() {
        val user = userStub(id = null)
        every { repositoryMock.findByEmailOrNull(user.email) } returns userStub()
        assertThrows<BadRequestException> {
            service.insert(user)
        } shouldHaveMessage "User already exists"
    }

    @Test
    fun `Insert must return the saved user if it's inserted`() {
        val user = userStub(id = null)
        every { repositoryMock.findByEmailOrNull(user.email) } returns null

        val saved = userStub()
        every { repositoryMock.save(user) } returns saved
        service.insert(user) shouldBe saved
    }

    @Test
    fun `findAll should delegate to repository`() {
        val sortDir = SortDir.values().random()
        val userList = listOf(userStub(1), userStub(2), userStub(3))
        every { repositoryMock.findAll(sortDir) } returns userList
        service.findAll(sortDir) shouldBe userList
    }

    @Test
    fun `findByIdOrNull should delegate to repository`() {
        val user = userStub()
        every { repositoryMock.findByIdOrNull(1) } returns user
        service.findByIdOrNull(1) shouldBe user
    }

    @Test
    fun `delete must return false if the user does not exists`() {
        every { repositoryMock.findByIdOrNull(1) } returns null
        service.delete(1) shouldBe false
    }

    @Test
    fun `delete must call delete and return true if the user exists`() {
        val user = userStub()
        every { repositoryMock.findByIdOrNull(1) } returns user
        every { repositoryMock.delete(user) } returns null
        service.delete(1) shouldBe true
    }
}