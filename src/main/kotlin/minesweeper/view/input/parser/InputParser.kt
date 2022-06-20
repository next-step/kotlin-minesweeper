package minesweeper.view.input.parser

fun interface InputParser<T> {
    fun parseValue(inputString: String?): ParseResult<T>
}
