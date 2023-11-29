package view

import business.Board
import business.BoardInfo
import business.Point

class ConsoleUserInterface : UserInterface {
    override fun askBoardInfo(): BoardInfo {
        val height = askHeight()
        val width = askWidth()
        val mineCount = askMineCount()
        println(START_MESSAGE)
        return BoardInfo(height, width, mineCount)
    }

    override fun askPoint(): Point {
        print(ASK_POINT_MESSAGE)
        val input = readlnOrNull() ?: throw IllegalArgumentException()
        val split = input.split(POINT_DELIMITER)
        require(split.size == POINT_SIZE) { POINT_SIZE_ERROR_MESSAGE }
        require(split.all { it.toIntOrNull() != null }) { POINT_NUMBER_ERROR_MESSAGE }
        val (height, width) = split.map { it.toInt() }
        return Point(height, width)
    }

    override fun displayOpenResult(board: Board) {
        board.executeWithOpenStatusAndMineCount({ isOpened: Boolean, count: Int ->
            printOpenedResult(
                isOpened, count
            )
        }) { println() }.let { println() }
    }

    override fun printWin() = println(GAME_WIN_MESSAGE)

    override fun displayGameOver(board: Board) {
        println(GAME_OVER_MESSAGE)
        board.executeWithMineStatusAndCount({ isMines: Boolean, count: Int ->
            printAll(
                isMines, count
            )
        }) { println() }.let { println() }
    }

    private fun askHeight(): Int {
        println(ASK_HEIGHT_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(HEIGHT_ERROR_MESSAGE)
    }

    private fun askWidth(): Int {
        println(ASK_WIDTH_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(WIDTH_ERROR_MESSAGE)
    }

    private fun askMineCount(): Int {
        println(ASK_MINE_COUNT_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(MINE_COUNT_ERROR_MESSAGE)
    }

    private fun printOpenedResult(isOpen: Boolean, count: Int) {
        if (!isOpen) {
            print(NOT_OPENED_DELIMITER + MINEFIELD_SEPARATOR)
            return
        }
        print(count.toString() + MINEFIELD_SEPARATOR)
    }

    private fun printAll(mines: Boolean, count: Int) {
        if (mines) {
            print(MINE_DELIMITER + MINEFIELD_SEPARATOR)
            return
        }
        print(count.toString() + MINEFIELD_SEPARATOR)
    }

    companion object {
        private const val POINT_SIZE = 2
        private const val POINT_SIZE_ERROR_MESSAGE = "쉼표로 구분된 두 개의 숫자여야 합니다."
        private const val POINT_NUMBER_ERROR_MESSAGE = "숫자여야 합니다."
        private const val ASK_HEIGHT_MESSAGE = "높이를 입력하세요."
        private const val ASK_WIDTH_MESSAGE = "너비를 입력하세요."
        private const val ASK_MINE_COUNT_MESSAGE = "지뢰의 개수를 입력하세요."
        private const val ASK_POINT_MESSAGE = "open: "
        private const val START_MESSAGE = "지뢰찾기 게임을 시작합니다."
        private const val GAME_OVER_MESSAGE = "Lose Game."
        private const val GAME_WIN_MESSAGE = "Win Game."
        private const val HEIGHT_ERROR_MESSAGE = "높이는 숫자여야 합니다."
        private const val WIDTH_ERROR_MESSAGE = "너비는 숫자여야 합니다."
        private const val MINE_COUNT_ERROR_MESSAGE = "지뢰의 개수는 숫자여야 합니다."
        private const val MINEFIELD_SEPARATOR = " "
        private const val POINT_DELIMITER = ","
        private const val MINE_DELIMITER = "*"
        private const val NOT_OPENED_DELIMITER = "C"
    }
}
