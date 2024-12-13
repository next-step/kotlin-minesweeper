package minesweeper.app

import minesweeper.converter.MineFieldConverter
import minesweeper.entity.Action
import minesweeper.entity.MineField
import minesweeper.entity.MineFieldFactory
import minesweeper.entity.RandomMineGenerator
import minesweeper.view.InputView
import minesweeper.view.OutputView

class Minesweeper {
    private val inputView = InputView()
    private val outputView = OutputView()
    private val mineFieldConverter = MineFieldConverter()

    fun setUp(): MineField {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()
        val mineFieldFactory = MineFieldFactory(RandomMineGenerator())
        return mineFieldFactory.create(height, width, mineCount)
    }

    fun gameStart(mineField: MineField) {
        outputView.printGameStart()

        while (true) {
            val openCoordinate = inputView.inputCoordinate()

            try {
                mineField.open(openCoordinate)
            } catch (e: IllegalArgumentException) {
                outputView.printInvalidCoordinate()
                continue
            }

            val action = mineField.determineAction()

            when (action) {
                Action.GAME_OVER -> {
                    outputView.printGameOver()
                    break
                }

                Action.GAME_CLEARED -> {
                    outputView.printGameCleared()
                    break
                }

                Action.CONTINUE -> Unit
            }
            printCurrentMineField(mineField)
        }
    }

    private fun printCurrentMineField(mineField: MineField) {
        val mineFieldViewData = mineFieldConverter.mapToViewData(mineField)
        outputView.printMineField(mineFieldViewData)
    }
}
