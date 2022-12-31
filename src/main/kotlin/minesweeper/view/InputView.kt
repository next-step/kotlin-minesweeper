package minesweeper.view

import minesweeper.domain.CellPosition
import minesweeper.domain.Position

object InputView {
    private const val DELIMITER = ","
    private const val POSITION_COUNT = 2

    fun requestCellPosition(): CellPosition {
        val input: String = requestString().trim()
        val positions = input.split(DELIMITER)
            .map { position -> position.toIntOrNull() ?: throw IllegalArgumentException("좌표는 숫자만 입력해야 합니다.") }

        require(positions.size == POSITION_COUNT) { "좌표는 x,y 2개만 입력 해야 합니다." }
        val (xPosition, yPosition) = positions

        return CellPosition(Position(xPosition), Position(yPosition))
    }

    fun requestPositiveNumber(): Int {
        val input: String = requestString()
        val number = input.toIntOrNull()

        require(number != null) { "숫자를 입력 해주세요." }
        require(number > 0) { "양수를 입력 해주세요." }

        return number
    }

    private fun requestString(): String {
        val input = readlnOrNull()
        require(!input.isNullOrBlank()) { "빈값을 입력 할 수 없습니다." }

        return input
    }
}
