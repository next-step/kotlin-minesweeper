package minesweeper.utils

object StringUtils {
    private const val COMMA = ","

    fun split(string: String): List<Int> = string.split(COMMA).map { it.toInt() }
}
