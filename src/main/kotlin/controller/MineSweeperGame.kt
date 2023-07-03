package controller

import model.CountedMineBoardProvider
import model.MineBoard
import model.MineBoardProvider
import model.MineInstallation
import model.minemark.Mine
import model.nextRandomPosition
import view.InputView
import view.OutputView

object MineSweeperGame {

    fun start() {
        val mineBoard: MineBoard = MineBoardProvider(InputView.length, InputView.width).mineBoard
        val mineSeeker = CountedMineBoardProvider(
            MineInstallation(InputView.mineCount, Mine(), ::nextRandomPosition)
                .installed(mineBoard)
        )
        OutputView.printBoard(mineSeeker.countedMineBoard)
    }
}

fun main() {
    MineSweeperGame.start()
}
