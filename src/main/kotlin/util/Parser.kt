package util

object Parser {
    private const val EMPTY_STRING = ""
    private val REPLACE_REGEX = "\\s+".toRegex()
    private val SPLIT_REGEX = ",".toRegex()

    fun parse(rawString: String): List<Int> {
        val removeSpaceStr = rawString.replace(REPLACE_REGEX, EMPTY_STRING)
        val splitStr = removeSpaceStr.split(SPLIT_REGEX)
        return splitStr.map { it.toInt() }
    }
}
