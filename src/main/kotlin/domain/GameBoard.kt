package domain

import enum.CellStatus
import enum.GameState

class GameBoard(private val mineManager: MineManager) {
    private lateinit var board: Board
    var gameStatus: GameState = GameState.IN_PROGRESS
    val isGameOver: Boolean
        get() = gameStatus != GameState.IN_PROGRESS

    val boardWidth: Int
        get() = board.width

    fun setupBoard(height: Int, width: Int) {
        board = Board.create(height, width, mineManager)
    }

    fun placeMines(mineCount: Int, firstMove: Position) {
        board.placeMines(mineCount, firstMove)
    }

    fun countMinesAround(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(board, position)
    }

    fun processEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        board.processEachCell(onEachCell)
    }

    fun openCell(position: Position) {
        if (gameStatus != GameState.IN_PROGRESS) return

        val cell = board.findCell(position)
        if (cell.isMine) {
            gameStatus = GameState.LOST
            return
        }

        openSafeCell(position)
        checkWinCondition()
    }

    private fun openSafeCell(position: Position) {
        val cell = board.findCell(position)
        if (cell.shouldOpen) {
            cell.open()
        }
    }

    private fun checkWinCondition() {
        if (board.isWinConditionMet()) {
            gameStatus = GameState.WON
        }
    }
}
