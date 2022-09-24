package v2minesweeper.controller

import v2minesweeper.domain.Height
import v2minesweeper.domain.MineNumber
import v2minesweeper.domain.MineSweeperBoardFactory
import v2minesweeper.domain.Width
import v2minesweeper.domain.createRandomMineSweeperBoard
import v2minesweeper.domain.toPosition
import v2minesweeper.view.InputView
import v2minesweeper.view.OutputView

object MineSweeperBoardV2Controller {
    fun run() {
        val mineSweeperBoard = MineSweeperBoardFactory(
            height = Height(InputView.inputHeight()),
            width = Width(InputView.inputWidth()),
            mineNumber = MineNumber(InputView.inputMineNumber())
        ) { height, width, mineNumber ->
            createRandomMineSweeperBoard(
                height = height,
                width = width,
                mineNumber = mineNumber
            )
        }.operate()

        OutputView.printInitMineSweeperBoard(mineSweeperBoard)

        while (mineSweeperBoard.isPlaying()) {
            try {
                mineSweeperBoard.open(InputView.inputOpenPosition().toPosition())
            } catch (e: Exception) {
                println(e.message)
            }
            OutputView.printCurrentMineSweeperBoard(mineSweeperBoard)
        }

        OutputView.printGameResult(mineSweeperBoard.getResult())
    }
}
