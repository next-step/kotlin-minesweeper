package controller

import domain.board.Board
import domain.setting.Height
import domain.setting.MineCount
import domain.setting.Size
import domain.setting.Width
import view.InputView
import view.OutputView

class MinesweeperController {
    fun makeBoard() {
        val boardSetting: BoardSetting = InputView.getBoardSetting()
        val board = Board.create(boardSetting.size, boardSetting.mineCount)
        OutputView.printBoard(board)
    }
}

data class BoardSetting(val height: Height, val width: Width, val mineCount: MineCount) {
    val size: Size get() = Size(height, width)
}
