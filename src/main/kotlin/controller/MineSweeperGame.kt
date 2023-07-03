package controller

import model.CountedMineBoard
import model.CountedMineBoardProvider
import model.MineBoard
import model.MineBoardOpener
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

        val result: CountedMineBoard = startMineSweeper(mineSeeker.countedMineBoard)
        if (result.isClosedMineAll) {
            OutputView.printWin()
        } else {
            OutputView.printLose()
        }
    }

    private fun startMineSweeper(countedMineBoard: CountedMineBoard): CountedMineBoard {
        var currentCountedMineBoard = countedMineBoard
        OutputView.printStart()
        do {
            currentCountedMineBoard = MineBoardOpener(currentCountedMineBoard).opened(InputView.openPosition)
            OutputView.printBoard(currentCountedMineBoard)
        } while (currentCountedMineBoard.isClosedMineCountAny && currentCountedMineBoard.isClosedMineAll)
        return currentCountedMineBoard
    }
}

fun main() {
    MineSweeperGame.start()
}
