package travelator.tablereader

fun readTableWithHeader(
    lines: List<String>
): List<Map<String, String>> = when {
    lines.isEmpty() -> emptyList()
    else -> readTable(
        lines.drop(1),
        headerProviderFrom(lines.first())
    )
}

fun headerProviderFrom(header: String): (Int) -> String {
    val headers = header.splitFields(",")
    return { index -> headers[index] }
}

fun readTable(
    lines: List<String>, headerProvider: (Int) -> String = Int::toString
): List<Map<String, String>> = lines.map { parseLine(it, headerProvider) }

private fun parseLine(line: String, headerProvider: (Int) -> String): Map<String, String> {
    val values = line.splitFields(",")
    val keys = values.indices.map(headerProvider)
    return keys.zip(values).toMap()
}

private fun String.splitFields(separators: String) = if (separators.isEmpty()) emptyList() else split(separators)
