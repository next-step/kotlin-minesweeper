package minesweeper.view.input

import minesweeper.view.input.parser.InputParser
import minesweeper.view.input.parser.ParseResult

object ConsoleReader {

    tailrec fun <T> read(message: String, parser: InputParser<T>, inputAtNewLine: Boolean = true): T {
        if (message.isNotEmpty()) {
            print(message)
        }
        if (inputAtNewLine) {
            println()
        }

        return when (val parsedValue = tryToRead(parser)) {
            is ParseResult.Value -> parsedValue.value
            is ParseResult.Error -> {
                println(parsedValue.error.message ?: "")
                read(message, parser, inputAtNewLine)
            }
        }
    }

    private fun <T> tryToRead(inputParser: InputParser<T>): ParseResult<T> =
        parseString(readLine(), inputParser)

    private fun <T> parseString(inputString: String?, inputParser: InputParser<T>): ParseResult<T> {
        if (inputString.isNullOrBlank()) {
            return ParseResult.Error("빈 문자열이 입력되었습니다.")
        }
        return inputParser.parseValue(inputString)
    }
}
