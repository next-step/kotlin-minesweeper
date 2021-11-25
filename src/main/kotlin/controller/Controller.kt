package controller

import domain.Board
import domain.MineLayer
import domain.MineNumber
import domain.Position
import domain.RandomPositionSelector
import dto.BoardDto
import view.InputView
import view.OutputView

object Controller {
    fun run() {
        val board = Board(InputView.askHeight(), InputView.askWidth())
        val selector = RandomPositionSelector(board)
        val mineLayer = MineLayer(board, selector)
        layMines(mineLayer)
        OutputView.printBoard(BoardDto(board))
    }

    private fun layMines(mineLayer: MineLayer) {
        val mineNumber = MineNumber(InputView.askMineNumber())
        OutputView.printStart()
        val position = Position()
        mineLayer.layMines(mineNumber, position)
    }
}
