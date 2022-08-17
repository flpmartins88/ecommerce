package br.com.ecommerce.infra.rest

import br.com.ecommerce.domain.customer.Customer
import br.com.ecommerce.domain.customer.CustomerRepository
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Test
    fun `should return a list of customers`() {

        customerRepository.save(Customer("Customer 001"))
        customerRepository.save(Customer("Customer 002"))

        mockMvc.get("/customers") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content {
                jsonPath("$", Matchers.hasSize<Customer>(2))
            }
        }
    }
}
