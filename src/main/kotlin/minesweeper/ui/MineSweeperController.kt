package minesweeper.ui

import minesweeper.application.MineSweeperService
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

        mineSweeperOutput.printMineBoard(mineBoard)
    }
}
