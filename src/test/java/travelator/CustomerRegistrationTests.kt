package travelator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import travelator.handlers.RegistrationData
import java.util.*

class CustomerRegistrationTests {
    var customers: InMemoryCustomers = InMemoryCustomers()
    var excluded: Set<String> = setOf(
        "cruella@hellhall.co.uk"
    )
    var registration: CustomerRegistration = CustomerRegistration(
        customers
    ) { registrationData: RegistrationData -> excluded.contains(registrationData.email) }

    @Test
    @Throws(DuplicateException::class, ExcludedException::class)
    fun adds_a_customer_when_not_excluded() {
        Assertions.assertEquals(Optional.empty<Any>(), customers.find("0"))

        val added = registration.register(
            RegistrationData("fred flintstone", "fred@bedrock.com")
        )
        Assertions.assertEquals(
            Customer("0", "fred flintstone", "fred@bedrock.com"),
            added
        )
        Assertions.assertEquals(added, customers.find("0").orElseThrow())
    }

    @Test
    fun throws_DuplicateException_when_email_address_exists() {
        customers.add(Customer("0", "fred flintstone", "fred@bedrock.com"))
        Assertions.assertEquals(1, customers.size())

        Assertions.assertThrows(
            DuplicateException::class.java
        ) {
            registration.register(
                RegistrationData("another name", "fred@bedrock.com")
            )
        }
        Assertions.assertEquals(1, customers.size())
    }

    @Test
    fun throws_ExcludedException_when_excluded() {
        Assertions.assertThrows(
            ExcludedException::class.java
        ) {
            registration.register(
                RegistrationData("cruella de vil", "cruella@hellhall.co.uk")
            )
        }
        Assertions.assertEquals(0, customers.size())
    }
}
