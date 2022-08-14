package br.com.ecommerce.domain.customer

class CustomerNotFound(id: Long) : Exception("Customer with id '$id' was not found!")
