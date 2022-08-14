package br.com.ecommerce.domain.item

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Item(

    @Column(name = "name")
    private var name: String,

    @Column(name = "price")
    private var price: Long

) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
}
