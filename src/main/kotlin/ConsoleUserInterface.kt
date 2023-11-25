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

    override fun printStartAnnouncement() = println(START_MESSAGE)

    override fun printMinefieldMatrix(minefieldMatrix: List<List<Int>>) = minefieldMatrix.forEach {
        println(
            it.joinToString(
                separator = SEPARATOR,
                transform = { cell -> if (cell == Const.MINE_VALUE) MINE_DELIMITER else cell.toString() })
        )
    }

    override fun printMinefieldMatrix(maxHeight: Int, maxWidth: Int, mines: Mines) {
        for (currentHeight in 0 until maxHeight) {
            printMinefieldRow(currentHeight, maxWidth, mines)
        }
    }

    private fun printMinefieldRow(targetHeight: Int, maxWidth: Int, mines: Mines) {
        for (currentWidth in 0 until maxWidth) {
            printPoint(targetHeight, currentWidth, mines)
        }
        println()
    }

    private fun printPoint(targetHeight: Int, currentWidth: Int, mines: Mines) {
        val point = Point(targetHeight, currentWidth)
        if (mines.contains(point)) {
            print(MINE_DELIMITER)
            print(SEPARATOR)
            return
        }
        print(mines.countMineAround(point))
        print(SEPARATOR)
    }

    companion object {
        private const val ASK_HEIGHT_MESSAGE = "높이를 입력하세요."
        private const val ASK_WIDTH_MESSAGE = "너비를 입력하세요."
        private const val ASK_MINE_COUNT_MESSAGE = "지뢰의 개수를 입력하세요."
        private const val START_MESSAGE = "지뢰찾기 게임을 시작합니다."
        private const val HEIGHT_ERROR_MESSAGE = "높이는 숫자여야 합니다."
        private const val WIDTH_ERROR_MESSAGE = "너비는 숫자여야 합니다."
        private const val MINE_COUNT_ERROR_MESSAGE = "지뢰의 개수는 숫자여야 합니다."
        private const val SEPARATOR = " "
        private const val MINE_DELIMITER = "*"
    }
}
