package br.com.ecommerce.infra.rest

import br.com.ecommerce.domain.customer.Customer
import br.com.ecommerce.domain.customer.CustomerService
import br.com.ecommerce.infra.rest.dto.CreateCustomer
import br.com.ecommerce.infra.rest.dto.CustomerResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping
    fun getAll(): List<CustomerResponse> {
        return customerService.getAll().toDto()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): CustomerResponse {
        return customerService.getOne(id).toDto()
    }

    @PostMapping
    fun create(customer: CreateCustomer): CustomerResponse {
        return customer.toDomain()
            .let { customerService.create(it) }
            .toDto()
    }

}

private fun CreateCustomer.toDomain(): Customer {
    return Customer(name = this.name)
}

private fun Customer.toDto(): CustomerResponse {
    return CustomerResponse(
        id = this.id!!,
        name = this.name
    )
}

private fun List<Customer>.toDto(): List<CustomerResponse> {
    return this.map { it.toDto() }
}
