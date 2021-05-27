package mongo.client.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication
class DemoApplication(val customerRepository: CustomerRepository) {

	@PostConstruct
	fun letsTestIt() {
		val customer = Customer(UUID.randomUUID(), "Buddy", "Bob")
		customerRepository.save(customer)

		val buddy = customerRepository.findByFirstName("Buddy")
		println("Our best customer => ${customer.firstName} ${customer.lastName}" )

	}
}
fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
data class Customer(val id: UUID, val firstName: String, val lastName: String?)

@Repository
interface CustomerRepository : MongoRepository<Customer, String?> {
	fun findByFirstName(firstName: String?): Customer
}
