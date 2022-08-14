package br.com.ecommerce.domain.customer

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun getAll(): List<Customer> {
        return customerRepository.findAll()
    }

    fun getOne(id: Long): Customer {
        return customerRepository.findByIdOrNull(id)
            ?: throw CustomerNotFound(id)
    }

    fun create(newCustomer: Customer): Customer {
        return customerRepository.save(newCustomer)
    }

}
