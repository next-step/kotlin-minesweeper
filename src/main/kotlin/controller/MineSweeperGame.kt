package controller

import model.CountedMineBoard
import model.CountedMineBoardProvider
import model.FilledElements
import model.FilledElementsProvider
import model.MineBoardOpener
import model.MineInstallation
import model.minemark.Mine
import model.nextRandomPosition
import view.InputView
import view.OutputView

object MineSweeperGame {

    fun start() {
        val filledElements: FilledElements = FilledElementsProvider(InputView.length, InputView.width).filledElements
        val mineSeeker = CountedMineBoardProvider(
            MineInstallation(
                count = InputView.mineCount,
                mark = Mine(),
                nextPosition = ::nextRandomPosition
            ).installedMineBoard(filledElements)
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
            currentCountedMineBoard = MineBoardOpener(currentCountedMineBoard).openedCountedMineBoard(InputView.openPosition)
            OutputView.printBoard(currentCountedMineBoard)
        } while (currentCountedMineBoard.isClosedMineCountAny && currentCountedMineBoard.isClosedMineAll)
        return currentCountedMineBoard
    }
}

fun main() {
    MineSweeperGame.start()
}
