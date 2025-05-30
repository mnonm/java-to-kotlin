package travelator.marketing

object HighValueCustomersMain {
    fun main(args: Array<String>) {
        System.`in`.reader().use { reader ->
            System.out.writer().use { writer ->
                writer.append(
                    generate(
                        reader.readLines()
                    ).joinToString("\n")
                )
            }
        }
    }
}
