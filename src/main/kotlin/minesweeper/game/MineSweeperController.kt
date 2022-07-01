package minesweeper.game

import minesweeper.domain.board.BoardStatus
import minesweeper.domain.board.MineBoard
import minesweeper.domain.board.mineBoard
import minesweeper.domain.cell.CellMaker
import minesweeper.view.ViewResolver

class MineSweeperController(
    private val viewResolver: ViewResolver,
    private val cellMaker: CellMaker
) {
    private var boardStatus: BoardStatus = BoardStatus.SAFE

    fun start() {
        val mineBoard = initMineBoard()
        viewResolver.printStartOfGame(mineBoard)
        do {
            playNextMove(mineBoard)
        } while (boardStatus == BoardStatus.SAFE)
        endGame()
    }

    private fun initMineBoard(): MineBoard {
        val request = viewResolver.inputMineBoardRequest()
        val mineBoard = mineBoard {
            width(request.width)
            height(request.height)
            numberOfMines(request.numberOfMines)
            cellMaker(cellMaker)
        }
        return mineBoard
    }

    private fun playNextMove(mineBoard: MineBoard) {
        val (x, y) = viewResolver.inputPositionToOpenCell()
        val cell = mineBoard.cells.first { it.position.x == x && it.position.y == y }
        boardStatus = mineBoard.open(cell.position)
        viewResolver.printMineBoard(mineBoard)
    }

    private fun endGame() {
        when (boardStatus) {
            BoardStatus.CLEAR -> println("Win Game!")
            BoardStatus.BOOM -> println("Lose Game.")
            BoardStatus.SAFE -> throw AssertionError()
        }
    }
}
