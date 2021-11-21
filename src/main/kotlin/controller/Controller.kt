package controller

import domain.Board
import domain.MineLayer
import domain.RandomPositionSelector
import dto.BoardDto
import view.InputView
import view.OutputView

object Controller {
    fun run() {
        val board = askBoard()
        val selector = RandomPositionSelector(board)
        val mineLayer = MineLayer(board, selector)
        layMines(mineLayer)
        printBoard(board)
    }

    private fun layMines(mineLayer: MineLayer) {
        val mineNumber = askMineNumber()
        printStart()
        mineLayer.layMines(mineNumber)
    }

    private fun askBoard(): Board = Board(InputView.askHeight(), InputView.askWidth())
    private fun askMineNumber(): Int = InputView.askMineNumber()
    private fun printStart() = OutputView.printStart()
    private fun printBoard(board: Board) = OutputView.printBoard(BoardDto(board))
}
