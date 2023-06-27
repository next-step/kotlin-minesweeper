package controller

import model.MineBoard
import model.MineBoardProvider
import model.MineInstallation
import model.MineMark
import model.nextRandomPosition
import view.InputView
import view.OutputView

object MineSweeperGame {

    fun start() {
        val mineBoard: MineBoard = MineBoardProvider(InputView.length, InputView.width).mineBoard
        val installedMine: MineBoard = MineInstallation(InputView.mineCount, MineMark.MINE, ::nextRandomPosition)
            .installed(mineBoard)
        OutputView.printBoard(installedMine)
    }
}

fun main() {
    MineSweeperGame.start()
}
