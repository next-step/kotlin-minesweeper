package domain

object InputParser {
    private const val DEFAULT_DELIMITER = ","

    fun parse(input: String, delimiter: String = DEFAULT_DELIMITER): List<String> {
        return input.split(delimiter).map { it.trim() }
    }
}
