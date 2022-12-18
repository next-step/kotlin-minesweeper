package controller

import domain.Board
import domain.Height
import domain.MineCnt
import domain.Width
import view.InputView
import view.OutputView

class MineSweeperController {
    fun execute() {
        val mineInputDto = InputView.readMineSweeperInput()
        val board = Board(
            height = Height(mineInputDto.height),
            width = Width(mineInputDto.width),
            mineCnt = MineCnt(mineInputDto.mineCnt)
        )
        OutputView.printBoard(board)
    }
}
