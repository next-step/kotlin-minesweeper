package view

import dto.BoardDto

object OutputView {
    private const val START_MINE_SWEEPER = "지뢰찾기 게임 시작"
    private const val DELIMITER = " "
    private const val WIN_GAME = "Win Game."
    private const val LOSE_GAME = "Lose Game."

    fun printStartGame() {
        println(START_MINE_SWEEPER)
    }

    fun printBoard(boardDto: BoardDto) {
        boardDto.forEach { row ->
            println(row.joinToString(DELIMITER))
        }
    }

    fun printWinGame() {
        println(WIN_GAME)
    }

    fun printLoseGame() {
        println(LOSE_GAME)
    }
}
