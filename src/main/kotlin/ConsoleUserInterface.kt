class ConsoleUserInterface : UserInterface {
    override fun askHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("높이는 숫자여야 합니다.")
    }

    override fun askWidth(): Int {
        println("너비를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("너비는 숫자여야 합니다.")
    }

    override fun askMineCount(): Int {
        println("지뢰의 개수를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("지뢰의 개수는 숫자여야 합니다.")
    }

    override fun printStartAnnouncement() = println("지뢰찾기 게임을 시작합니다.")

    override fun printGameBoard(gameBoard: List<List<Int>>) = gameBoard.forEach {
        println(
            it.joinToString(
                separator = " ",
                transform = { cell ->
                    when (cell) {
                        -1 -> "*"
                        else -> cell.toString()
                    }
                }
            )
        )
    }
}
