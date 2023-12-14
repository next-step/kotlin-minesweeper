package game.minesweeper.ui

import game.minesweeper.domain.GameBoard

object Input {
    private const val HEIGHT_PROMPT = "높이를 입력하세요."
    private const val WIDTH_PROMPT = "너비를 입력하세요."
    private const val NUMBER_OF_MINES_PROMPT = "지뢰는 몇 개인가요?"

    private const val INVALID_NUMBER_PROMPT = "유효한 숫자를 입력해야 합니다."
    private const val INVALID_HEIGHT_RANGE_PROMPT = "높이는 0보다 커야 합니다."
    private const val INVALID_WIDTH_RANGE_PROMPT = "너비는 0보다 커야 합니다."
    private const val INVALID_MINES_NUMBER_PROMPT = "지뢰 개수는 0보다 커야 합니다."

    fun getHeight(): Int {
        println(HEIGHT_PROMPT)

        val height = readlnOrNull()?.toIntOrNull()
        requireNotNull(height) { INVALID_NUMBER_PROMPT }
        require(height > 0) { INVALID_HEIGHT_RANGE_PROMPT }

        return height
    }

    fun getWidth(): Int {
        println(WIDTH_PROMPT)

        val width = readlnOrNull()?.toIntOrNull()
        requireNotNull(width) { INVALID_NUMBER_PROMPT }
        require(width > 0) { INVALID_WIDTH_RANGE_PROMPT }

        return width
    }

    fun getMinesNumber(): Int {
        println(NUMBER_OF_MINES_PROMPT)

        val minesNumber = readlnOrNull()?.toIntOrNull()
        requireNotNull(minesNumber) { INVALID_NUMBER_PROMPT }
        require(minesNumber > 0) { INVALID_MINES_NUMBER_PROMPT }

        return minesNumber
    }
}

object Output {
    private const val GAME_START_PROMPT = "지뢰찾기 게임 시작"

    fun printStartGamePrompt() {
        println(GAME_START_PROMPT)
    }

    fun printGameResult(gameBoard: GameBoard) {
        println(gameBoard)
    }
}
