package travelator.marketing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.StringWriter

internal class HighValueCustomersReportTests {
    @Test
    fun test() {
        val input = listOf(
            "ID\tFirstName\tLastName\tScore\tSpend",
            "1\tFred\tFlintstone\t11\t1000.00",
            "4\tBetty\tRubble\t10\t2000.00",
            "2\tBarney\tRubble\t0\t20.00",
            "3\tWilma\tFlintstone\t9\t0.00"
        )
        val expected = listOf(
            "ID\tName\tSpend",
            "4\tRUBBLE, Betty\t2000.00",
            "1\tFLINTSTONE, Fred\t1000.00",
            "\tTOTAL\t3000.00"
        )
        check(input, expected)
    }

    @Test
    fun emptyTest() {
        val input = listOf(
            "ID\tFirstName\tLastName\tScore\tSpend"
        )
        val expected = listOf(
            "ID\tName\tSpend",
            "\tTOTAL\t0.00"
        )
        check(input, expected)
    }

    @Test
    fun emptySpendIs0() {
        assertEquals(
            CustomerData("1", "Fred", "Flintstone", 0, 0.0),
            "1\tFred\tFlintstone\t0".toCustomerData()
        )
    }

    private fun check(
        inputLines: List<String>,
        expectedLines: List<String>
    ) {
        assertEquals(expectedLines, generate(inputLines))
    }
}
