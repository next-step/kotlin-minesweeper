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
        board.firstSafeCell(mineCount, firstMove)
    }

    fun countMinesAround(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(board, position)
    }

    fun processEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        board.processEachCell(onEachCell)
    }

    fun openCell(position: Position) {
        if (gameStatus != GameState.IN_PROGRESS || !isCellValid(position)) return

        if (isMineCell(position)) {
            gameStatus = GameState.LOST
            return
        }

        openSafeCell(position)
        checkWinCondition()
    }

    private fun isCellValid(position: Position): Boolean {
        return board.findCell(position) != null
    }

    private fun isMineCell(position: Position): Boolean {
        return board.findCell(position)?.isMine ?: false
    }

    private fun openSafeCell(position: Position) {
        board.findCell(position)?.open()
    }

    private fun checkWinCondition() {
        if (board.isWinConditionMet()) {
            gameStatus = GameState.WON
        }
    }
}
