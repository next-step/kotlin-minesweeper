package minesweeper.view

import minesweeper.domain.MineCreator
import minesweeper.domain.MineSweeper
import minesweeper.domain.MineSweeperCreator
import minesweeper.domain.MineSweeperSize
import minesweeper.domain.MineSweeperState

class MineSweeperController(
    inputView: InputView,
    resultView: ResultView
) {
    init {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val countOfMine = inputView.getCountOfMine()

        val mineSweeperSize = MineSweeperSize(width = width, height = height)
        val minePosition = MineCreator.create(mineSweeperSize, countOfMine)

        val mineSweeper = MineSweeper(
            MineSweeperCreator(
                mineSweeperSize = mineSweeperSize,
                minePosition = minePosition
            ).createMineSweeperMap()
        )

        resultView.showGameStart()

        while (true) {
            val openPosition = inputView.getPosition()
            val mineSweeperState = mineSweeper.openCell(openPosition)

            if (mineSweeperState == MineSweeperState.CONTINUE) {
                resultView.showMineSweeper(mineSweeper = mineSweeper)
                continue
            }

            if (mineSweeperState == MineSweeperState.WIN) {
                resultView.showWinGame()
            }

            if (mineSweeperState == MineSweeperState.LOSE) {
                resultView.showLoseGame()
            }
            break
        }
    }
}
