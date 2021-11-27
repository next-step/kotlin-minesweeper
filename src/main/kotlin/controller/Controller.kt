package controller

import domain.Board
import domain.MineLayer
import domain.MineNumber
import domain.MineSweeper
import domain.RandomPositionSelector
import dto.BoardDto
import view.InputView
import view.OutputView

object Controller {
    fun run() {
        val board = Board(InputView.askHeight(), InputView.askWidth())
        val selector = RandomPositionSelector(board)
        val mineLayer = MineLayer(board, selector)
        val mineSweeper = MineSweeper(board, selector)
        layMines(mineLayer, mineSweeper)
        sweepMines(board, mineSweeper)
    }

    private fun layMines(mineLayer: MineLayer, mineSweeper: MineSweeper) {
        val mineNumber = MineNumber(InputView.askMineNumber())
        OutputView.printStart()
        val position = InputView.askPosition()
        mineLayer.layMines(mineNumber, position)
        mineSweeper.sweepMine(position)
    }

    private fun sweepMines(board: Board, mineSweeper: MineSweeper) {
        do {
            OutputView.printBoard(BoardDto(board))
        } while (
            !board.isAllOpen() &&
            mineSweeper.sweepMine(
                InputView.askPosition()
            )
        )
        OutputView.printResult(board.isAllOpen())
    }
}
