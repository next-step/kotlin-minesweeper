package minesweeper.view.input.parser

import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.Position

class PositionInputParser(private val area: Area) : InputParser<Position> {

    override fun parseValue(inputString: String?): ParseResult<Position> {
        return when (val parsedInput = inputString.parseTwoInt()) {
            is ParseResult.Error -> ParseResult.Error(parsedInput.error)
            is ParseResult.Value -> validateResult(parsedInput)
        }
    }

    private fun String?.parseTwoInt(): ParseResult<IntArray> = runCatching {
        val towIntValues = this?.split(",")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() }
            ?.map { it.toInt() }
            ?.toIntArray() ?: IntArray(0)

        require(towIntValues.size == 2) { ERROR_MESSAGE_NUMBER_FORMAT }
        ParseResult.Value(towIntValues)
    }.getOrElse {
        ParseResult.Error(ERROR_MESSAGE_NUMBER_FORMAT)
    }

    private fun validateResult(parsedInput: ParseResult.Value<IntArray>): ParseResult<Position> = runCatching {

        val position = Position(
            row = parsedInput.value[0],
            column = parsedInput.value[1]
        )
        require(position.row in area.rowRange) {
            "행은 (첫째 숫자는) ${area.rowRange.first} ~ ${area.rowRange.last} 사이여야 합니다."
        }

        require(position.column in area.columnRange) {
            "열은 (두번째 숫자는) ${area.columnRange.first} ~ ${area.columnRange.last} 사이여야 합니다."
        }

        ParseResult.Value(position)
    }.getOrElse {
        ParseResult.Error(it.message ?: ERROR_MESSAGE_NUMBER_FORMAT)
    }

    companion object {
        const val ERROR_MESSAGE_NUMBER_FORMAT = " ,로 구분된 2개의 숫자로 입력해 주세요"
    }
}
