package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.CellType
import minesweeper.domain.MIN_SIZE
import minesweeper.domain.MinesweeperGame
import minesweeper.domain.MinesweeperGameResult
import minesweeper.domain.PlayState
import minesweeper.domain.PositionCheckResult

object ResultView {

    private const val START_NOTICE = "\n지뢰찾기 게임 시작\n"
    private const val FINISH_NOTICE = "\n종료 : "
    private const val LOAD_SYMBOL = "\uD83C\uDF2B️️"
    private const val MINE_CHARACTER = "\uD83D\uDCA3"
    private const val LOAD_CHARACTER = "\uD83D\uDFE9"

    fun showMinesweeperBoard(minesweeperGame: MinesweeperGame) {
        val symbolOfBoard = StringBuilder()
        minesweeperGame.minesweeperBoard.minesweeperBoard.forEach { it ->
            it.forEach { symbolOfBoard.append(" ${getCellSymbol(it)} ") }
            symbolOfBoard.append("\n")
        }
        println(symbolOfBoard.addMessageForPlayState(minesweeperGame.playState))
    }

    private fun StringBuilder.addMessageForPlayState(playState: PlayState): StringBuilder {
        if (playState == PlayState.READY) this.insert(0, START_NOTICE)
        if (playState == PlayState.LOSE || playState == PlayState.WIN) this.append(FINISH_NOTICE + playState.name)
        return this
    }

    fun showErrorMessage(message: String) {
        println(message)
    }

    fun showErrorMessage(minesweeperGameResult: MinesweeperGameResult) {
        println(showErrorMessage(getMessage(minesweeperGameResult)))
    }

    fun showErrorMessage(positionCheckResult: PositionCheckResult) {
        val message = getMessage(positionCheckResult)
        if (message.isNotEmpty()) println(message)
    }

    private fun getCellSymbol(cell: Cell): String {
        if (cell.isOpen) return getOpenCellSymbol(cell)
        return LOAD_SYMBOL
    }

    private fun getOpenCellSymbol(cell: Cell): String {
        if (cell.cellType != CellType.MINE && cell.numberOfNeighboringMine > 0) {
            return "${cell.numberOfNeighboringMine} "
        }
        if (cell.cellType == CellType.MINE) return MINE_CHARACTER
        return LOAD_CHARACTER
    }

    private fun getMessage(result: MinesweeperGameResult): String {
        return when (result) {
            is MinesweeperGameResult.Success -> "지뢰판이 만들어졌습니다."
            is MinesweeperGameResult.InvalidHeight -> "높이는 자연수로 입력해주세요."
            is MinesweeperGameResult.InvalidWidth -> "너비는 자연수로 입력해주세요."
            is MinesweeperGameResult.InvalidMineCount -> "지뢰개수는 자연수로 입력해주세요."
        }
    }

    private fun getMessage(result: PositionCheckResult): String {
        return when (result) {
            is PositionCheckResult.Success -> ""
            is PositionCheckResult.InvalidateExpression -> "높이는 자연수로 입력해주세요."
            is PositionCheckResult.InvalidateY -> "열의 위치는 $MIN_SIZE ~ ${result.boardSize.width.length} 사이의 숫자를 입력해주세요"
            is PositionCheckResult.InvalidateX -> "행의 위치는 $MIN_SIZE ~ ${result.boardSize.height.length} 사이의 숫자를 입력해주세요"
        }
    }
}
