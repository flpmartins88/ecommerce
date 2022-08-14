package br.com.ecommerce.domain.customer

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull

@ExtendWith(MockKExtension::class)
internal class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @InjectMockKs
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers when exists more than 1`() {
        // Given
        val customers = listOf(
            Customer("João"),
            Customer("Maria")
        )
        every { customerRepository.findAll() } returns customers

        // When
        val result = customerService.getAll()

        // Then
        assertThat(result).hasSameSizeAs(customers)
        assertThat(result).containsAll(customers)

        verify(exactly = 1) { customerRepository.findAll() }
    }

    @Test
    fun `should return one customer when exists only one`() {
        // Given
        val customers = listOf(Customer("João"))
        every { customerRepository.findAll() } returns customers

        // When
        val result = customerService.getAll()

        // Then
        assertThat(result).hasSameSizeAs(customers)
        assertThat(result).containsAll(customers)

        verify(exactly = 1) { customerRepository.findAll() }
    }

    @Test
    fun `should return an empty list when does not exists customers`() {
        // Given
        val customers = emptyList<Customer>()
        every { customerRepository.findAll() } returns customers

        // When
        val result = customerService.getAll()

        // Then
        assertThat(result).isEmpty()

        verify(exactly = 1) { customerRepository.findAll() }
    }

    @Test
    fun `should get by id when exists`() {
        // Given
        val customerId = 1L
        val customer = Customer("João")

        every { customerRepository.findByIdOrNull(1L) } returns customer

        // When
        val result = customerService.getOne(customerId)

        // Then
        assertThat(result).isEqualTo(customer)

        verify(exactly = 1) { customerRepository.findByIdOrNull(1L) }
    }

    @Test
    fun `should throw an exception when customer id does not exists`() {
        // Given
        val customerId = 1L
        every { customerRepository.findByIdOrNull(customerId) } returns null

        // Then
        assertThrows<CustomerNotFound> { customerService.getOne(customerId) }

        verify(exactly = 1) { customerRepository.findByIdOrNull(customerId) }
    }

}
