package travelator.marketing

import java.util.*

fun generate(lines: List<String>): List<String> {
    val valuableCustomers = lines
        .withoutHeader()
        .map { line -> line.toCustomerData() }
        .filter { it.score >= 10 }
        .sortedBy(CustomerData::score)

    return listOf("ID\tName\tSpend") +
            valuableCustomers.map(CustomerData::outputLine) +
            valuableCustomers.summarised()
}

private fun List<String>.withoutHeader() = drop(1)
private fun List<CustomerData>.summarised(): String =
    sumOf { it.spend }.let { total ->
        "\tTOTAL\t${total.toMoneyString()}"
    }

internal fun String.toCustomerData(): CustomerData =
    split("\t".toRegex()).dropLastWhile { it.isEmpty() }.let { parts ->
        CustomerData(
            id = parts[0],
            givenName = parts[1],
            familyName = parts[2],
            score = parts[3].toInt(),
            spend = if (parts.size == 4) 0.0 else parts[4].toDouble()
        )
    }

private val CustomerData.outputLine: String
    get() = "$id\t$marketingName\t${spend.toMoneyString()}"

private fun Double.toMoneyString() = this.formattedAs("%#.2f")
private fun Any?.formattedAs(format: String) = String.format(format, this)
private val CustomerData.marketingName: String
    get() = familyName.uppercase(Locale.getDefault()) + ", " + givenName
