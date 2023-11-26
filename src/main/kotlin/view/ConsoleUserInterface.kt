package view

import business.Mines
import business.OpenedCells
import business.Point

class ConsoleUserInterface : UserInterface {
    override fun askHeight(): Int {
        println(ASK_HEIGHT_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(HEIGHT_ERROR_MESSAGE)
    }

    override fun askWidth(): Int {
        println(ASK_WIDTH_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(WIDTH_ERROR_MESSAGE)
    }

    override fun askMineCount(): Int {
        println(ASK_MINE_COUNT_MESSAGE)
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException(MINE_COUNT_ERROR_MESSAGE)
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

    override fun printStartAnnouncement() = println(START_MESSAGE)

    override fun printMinefieldMatrix(maxHeight: Int, maxWidth: Int, mines: Mines) {
        for (currentHeight in ZERO.until(maxHeight)) printMinefieldRow(currentHeight, maxWidth, mines)
    }

    override fun printGameOver() = println(GAME_OVER_MESSAGE)

    override fun printWin() = println(GAME_WIN_MESSAGE)

    override fun printOpenedMinefieldMatrix(maxHeight: Int, maxWidth: Int, openedCells: OpenedCells, mines: Mines) {
        for (currentHeight in ZERO.until(maxHeight)) printOpenedMinefieldRow(
            currentHeight,
            maxWidth,
            openedCells,
            mines
        )
    }

    private fun printOpenedMinefieldRow(targetHeight: Int, maxWidth: Int, openedCells: OpenedCells, mines: Mines) {
        for (currentWidth in ZERO.until(maxWidth)) {
            printOpenedMinefield(targetHeight, currentWidth, openedCells, mines)
            print(MINEFIELD_SEPARATOR)
        }
        println()
    }

    private fun printOpenedMinefield(targetHeight: Int, currentWidth: Int, openedCells: OpenedCells, mines: Mines) {
        val point = Point(targetHeight, currentWidth)
        if (openedCells.contains(point)) {
            print(mines.countMineAround(point))
            return
        }
        print(NOT_OPENED_DELIMITER)
    }

    private fun printMinefieldRow(targetHeight: Int, maxWidth: Int, mines: Mines) {
        for (currentWidth in ZERO.until(maxWidth)) {
            printMinefield(targetHeight, currentWidth, mines)
            print(MINEFIELD_SEPARATOR)
        }
        println()
    }

    private fun printMinefield(targetHeight: Int, currentWidth: Int, mines: Mines) {
        val point = Point(targetHeight, currentWidth)
        if (mines.contains(point)) {
            print(MINE_DELIMITER)
            return
        }
        print(mines.countMineAround(point))
    }

    companion object {
        private const val ZERO = 0
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
