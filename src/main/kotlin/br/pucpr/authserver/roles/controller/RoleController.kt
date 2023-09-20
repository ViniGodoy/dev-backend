package br.pucpr.authserver.roles.controller

import br.pucpr.authserver.roles.RoleService
import br.pucpr.authserver.roles.controller.requests.CreateRoleRequest
import br.pucpr.authserver.roles.controller.responses.RoleResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/roles")
@SecurityRequirement(name = "AuthServer")
@PreAuthorize("hasRole('ADMIN')")
class RoleController(val service: RoleService) {
    @PostMapping
    fun insert(@Valid @RequestBody role: CreateRoleRequest) =
        RoleResponse(service.insert(role.toRole()))
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun list() = service.findAll()
        .map { RoleResponse(it) }
        .let { ResponseEntity.ok(it) }
}