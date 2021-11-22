package controller

import domain.Board
import domain.MineLayer
import domain.MineNumber
import domain.MineSweeper
import domain.Position
import domain.RandomPositionSelector
import dto.BoardDto
import view.InputView
import view.OutputView

object Controller {
    fun run() {
        val board = askBoard()
        val selector = RandomPositionSelector(board)
        val mineLayer = MineLayer(board, selector)
        val mineSweeper = MineSweeper(board, selector)
        layMines(mineLayer, mineSweeper)
        printBoard(board)
    }

    private fun layMines(mineLayer: MineLayer, mineSweeper: MineSweeper) {
        val mineNumber = askMineNumber()
        printStart()
        val position = Position()
        mineLayer.layMines(mineNumber, position)
        mineSweeper.sweepMine(position)
    }

    private fun sweepMines(board: Board, mineSweeper: MineSweeper) {
        do {
            printBoard(board)
        } while (!board.isAllOpen() && mineSweeper.sweepMine(askPosition()))
        printResult(board)
    }

    private fun askBoard(): Board = Board(InputView.askHeight(), InputView.askWidth())
    private fun askMineNumber(): MineNumber = MineNumber(InputView.askMineNumber())
    private fun askPosition(): Position = Position(InputView.askPosition())
    private fun printStart() = OutputView.printStart()
    private fun printResult(board: Board) = OutputView.printResult(board.isAllOpen())
    private fun printBoard(board: Board) = OutputView.printBoard(BoardDto(board))
}
