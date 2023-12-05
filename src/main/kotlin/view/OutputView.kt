package view

import dto.CellTypeDto
import dto.GameBoardDto
import dto.GameResultDto

object OutputView {

    private const val MINE = "*"
    private const val NOT_MINE = "C"
    private const val ENTER_HEIGHT = "높이를 입력하세요."
    private const val ENTER_WIDTH = "너비를 입력하세요."
    private const val ENTER_MINE_COUNT = "지뢰의 개수를 입력하세요."
    private const val MINE_GAME_START = "지뢰찾기 게임 시작"
    private const val PRINT_OPEN = "open: "
    private const val PRINT_LOSE_GAME = "Lose Game."
    private const val PRINT_WIN_GAME = "Win Game."

    fun printEnterHeight() = println(ENTER_HEIGHT)
    fun printEnterWidth() = println(ENTER_WIDTH)
    fun printEnterMineCount() = println(ENTER_MINE_COUNT)
    fun printMineGameStart() = println(MINE_GAME_START)
    fun printOpen() = print(PRINT_OPEN)
    private fun printLoseGame() = println(PRINT_LOSE_GAME)
    private fun printWinGame() = println(PRINT_WIN_GAME)

    fun printGameBoard(gameBoard: GameBoardDto) {
        gameBoard.board.forEach { row ->
            row.forEach { cell ->
                printCell(cell)
            }
            println()
        }
    }

    private fun printCell(cell: CellTypeDto) {
        val valueToPrint =
        if (cell.isOpened) {
            if (cell.symbol == MINE) cell.symbol else cell.count
        } else {
            NOT_MINE
        }
        print("$valueToPrint ")
    }

    fun printGameResult(gameResultDto: GameResultDto) {
        if (gameResultDto.status == "WIN") { printWinGame() }
        else { printLoseGame() }
    }
}
