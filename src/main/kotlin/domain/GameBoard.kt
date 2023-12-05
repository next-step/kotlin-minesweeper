package domain

import enum.CellStatus
import enum.GameState

class GameBoard(private val mineManager: MineManager) {
    private lateinit var board: Board
    var width: Int = 0
    private var height: Int = 0
    private var gameStatus: GameState = GameState.IN_PROGRESS
    val isGameOver: Boolean
        get() = gameStatus != GameState.IN_PROGRESS

    fun setupBoard(height: Int, width: Int) {
        this.height = height
        this.width = width
        board = Board(height, width, mineManager)
        board.closeAllCells()
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

    fun openCell(position: Position): Boolean {
        if (gameStatus != GameState.IN_PROGRESS) return false
        return openCellCommonLogic(position, checkMine = true)
    }

    fun openCellWithoutMineCheck(position: Position) {
        openCellCommonLogic(position, checkMine = false)
    }

    private fun openCellCommonLogic(position: Position, checkMine: Boolean): Boolean {
        val cell = board.findCell(position) ?: return false
        if (checkMine && cell.isMine) {
            gameStatus = GameState.LOST
            return false
        }

        cell.open()
        if (board.isWinConditionMet()) {
            gameStatus = GameState.WON
        }

        return true
    }
}
