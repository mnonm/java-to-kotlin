package travelator

import dev.forkhandles.result4k.failureOrNull
import dev.forkhandles.result4k.valueOrNull
import org.junit.jupiter.api.Assertions.assertEquals
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
    fun `adds a customer when not excluded`() {
        assertEquals(Optional.empty<Any>(), customers.find("0"))

        val added = registration.register(
            RegistrationData("fred flintstone", "fred@bedrock.com")
        ).valueOrNull()
        assertEquals(
            Customer("0", "fred flintstone", "fred@bedrock.com"),
            added
        )
        assertEquals(added, customers.find("0").orElseThrow())
    }

    @Test
    fun `returns Duplicate when email address exists`() {
        customers.add(Customer("0", "fred flintstone", "fred@bedrock.com"))
        assertEquals(1, customers.size())
        val failure = registration.register(
            RegistrationData("another name", "fred@bedrock.com")
        ).failureOrNull()
        assertEquals(
            Duplicate("customer with email fred@bedrock.com already exists"),
            failure
        )
        assertEquals(1, customers.size())
    }

    @Test
    fun `returns Excluded when excluded`() {
        val failure = registration.register(
            RegistrationData("cruella de vil", "cruella@hellhall.co.uk")
        ).failureOrNull()
        assertEquals(
            Excluded,
            failure
        )
        assertEquals(0, customers.size())
    }
}
