package view

import dto.GameBoardDto

object OutputView {

    private const val MINE = "*"
    private const val ENTER_HEIGHT = "높이를 입력하세요."
    private const val ENTER_WIDTH = "너비를 입력하세요."
    private const val ENTER_MINE_COUNT = "지뢰의 개수를 입력하세요."
    private const val MINE_GAME_START = "지뢰찾기 게임 시작"

    fun printEnterHeight() = println(ENTER_HEIGHT)
    fun printEnterWidth() = println(ENTER_WIDTH)
    fun printEnterMineCount() = println(ENTER_MINE_COUNT)
    fun printMineGameStart() = println(MINE_GAME_START)

    fun printGameBoard(gameBoard: GameBoardDto) {
        gameBoard.board.forEach { row ->
            row.forEach { cell ->
                if (cell.symbol == MINE) print(cell.symbol)
                else print(cell.count)
                print(" ")
            }
            println()
        }
    }
}
