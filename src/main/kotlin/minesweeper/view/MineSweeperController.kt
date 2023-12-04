package minesweeper.view

import minesweeper.domain.MineCreator
import minesweeper.domain.MineSweeper
import minesweeper.domain.MineSweeperCreator
import minesweeper.domain.MineSweeperSize

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

        resultView.showMineSweeper(mineSweeper = mineSweeper)
    }
}
