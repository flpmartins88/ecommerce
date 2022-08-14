package br.com.ecommerce.domain.order

import br.com.ecommerce.domain.customer.Customer
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Order(

    @ManyToOne
    val customer: Customer

) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

}
