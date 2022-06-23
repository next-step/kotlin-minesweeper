package minesweeper.parser

object InputParser {

    fun parseHeight(input: String): Int {
        return input.toInt().also {
            verify(it)
        }
    }

    fun parseWidth(input: String): Int {
        return input.toInt().also {
            verify(it)
        }
    }

    fun parseMinesCount(input: String): Int {
        return input.toInt().also {
            verify(it)
        }
    }

    private fun verify(input: Int) {
        require(input > 0) { "[$input] is invalid value" }
    }
}
