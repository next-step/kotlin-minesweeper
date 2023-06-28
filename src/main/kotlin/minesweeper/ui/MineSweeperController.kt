package minesweeper.ui

import minesweeper.application.MineSweeperService
import minesweeper.domain.MineBoard
import minesweeper.domain.Point
import minesweeper.domain.state.ResultState
import minesweeper.request.MineBoardCreateRequest

class MineSweeperController(
    private val mineSweeperInput: MineSweeperInput,
    private val mineSweeperOutput: MineSweeperOutput,
    private val mineSweeperService: MineSweeperService
) {

    fun play() {
        val request = MineBoardCreateRequest(
            width = mineSweeperInput.requestWidth(),
            height = mineSweeperInput.requestHeight(),
            mineCapacity = mineSweeperInput.requestMineCapacity()
        )

        val mineBoard = mineSweeperService.createMineBoard(request)

        mineSweeperOutput.printStartGame()
        playByMineBoard(mineBoard)
    }

    private fun playByMineBoard(mineBoard: MineBoard) {
        val requestPoint: Point = mineSweeperInput.requestMarkingPoint().let { Point(x = it[0] - 1, y = it[1] - 1) }

        runCatching {
            when (mineBoard.marking(requestPoint)) {
                ResultState.WIN -> mineSweeperOutput.printWin()
                ResultState.LOSE -> mineSweeperOutput.printLose()
                ResultState.CONTINUABLE -> {
                    mineSweeperOutput.printMineBoard(mineBoard)
                    playByMineBoard(mineBoard)
                }
            }
        }.recoverCatching {
            mineSweeperOutput.printException(it.message)
            playByMineBoard(mineBoard)
        }
    }
}
