package tdd.minesweeper.ui

import tdd.minesweeper.domain.AdjacentMines
import tdd.minesweeper.domain.Board
import tdd.minesweeper.domain.Cell
import tdd.minesweeper.domain.GameState

object ConsoleOutputView : OutputView {
    private const val DELIMITER = " "

    override fun printGameStarted() {
        println("지뢰찾기 게임 시작")
    }

    override fun displayBoard(board: Board) {
        board.cells
            .chunked(board.area.width)
            .forEach { row: List<Cell> ->
                println(row.joinToString(DELIMITER) { paintCell(it) })
            }
    }

    private fun paintCell(cell: Cell): String {
        return when {
            !cell.isOpen() -> "🌫️"
            cell.hasMine() -> "💥"
            cell.adjacentMines == AdjacentMines(0) -> "0️⃣"
            cell.adjacentMines == AdjacentMines(1) -> "1️⃣"
            cell.adjacentMines == AdjacentMines(2) -> "2️⃣"
            cell.adjacentMines == AdjacentMines(3) -> "3️⃣"
            cell.adjacentMines == AdjacentMines(4) -> "4️⃣"
            cell.adjacentMines == AdjacentMines(5) -> "5️⃣"
            cell.adjacentMines == AdjacentMines(6) -> "6️⃣"
            cell.adjacentMines == AdjacentMines(7) -> "7️⃣"
            cell.adjacentMines == AdjacentMines(8) -> "8️⃣"
            else -> throw IllegalStateException("정상적인 셀 상태가 아닙니다: cell=$cell")
        }
    }

    override fun printGameEnded(state: GameState) {
        return when (state) {
            GameState.WIN -> println("Win Game 🎉🎉🎉")
            GameState.LOSE -> println("Lose Game 🤯")
            else -> {}
        }
    }

    override fun printError(errorMessage: String) {
        println(errorMessage)
        println()
    }
}
