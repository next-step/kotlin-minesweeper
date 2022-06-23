package minesweeper.game

import minesweeper.domain.board.BoardStatus
import minesweeper.domain.board.MineBoard
import minesweeper.domain.board.MineMaker
import minesweeper.domain.board.mineBoard
import minesweeper.view.ViewResolver

class MineSweeperController(
    private val viewResolver: ViewResolver,
    private val mineMaker: MineMaker
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
            mineStrategy(mineMaker)
        }
        return mineBoard
    }

    private fun playNextMove(mineBoard: MineBoard) {
        val (x, y) = viewResolver.inputPositionToOpenCell()
        boardStatus = mineBoard.open(x, y)
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
