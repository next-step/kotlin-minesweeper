package minesweeper.app

import minesweeper.converter.MineFieldConverter
import minesweeper.entity.Cell
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
        while (true){
            val openCoordinate = inputView.inputCoordinate()
            mineField.open(openCoordinate)
            if(mineField.findCell(openCoordinate) is Cell.Mine){
                outputView.printGameOver()
                break
            }
            val mineFieldViewData = mineFieldConverter.mapToViewData(mineField)
            outputView.printMineField(mineFieldViewData)
            if(isGameCleared(mineField)){
                outputView.printGameCleared()
                break
            }
        }
    }
    private fun isGameCleared(mineField: MineField): Boolean {
        return mineField.cells
            .filterIsInstance<Cell.Empty>()
            .all { it.isRevealed }
    }
}
