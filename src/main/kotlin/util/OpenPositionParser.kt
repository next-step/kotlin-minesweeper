package util

object OpenPositionParser {

    private const val SEPARATOR = ","

    fun parse(openPosition: String): List<String> {
        return openPosition.split(SEPARATOR)
    }
}