package minesweeper.app

import minesweeper.entity.Cell
import minesweeper.entity.MineField
import minesweeper.entity.MineFieldFactory
import minesweeper.entity.RandomMineGenerator
import minesweeper.view.InputView
import minesweeper.view.OutputView

class Minesweeper {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun setUp(): MineField {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()
        val mineFieldFactory = MineFieldFactory(RandomMineGenerator())
        return mineFieldFactory.create(height, width, mineCount)
    }

    fun gameStart(mineField: MineField) {
        outputView.printGameStart()
        outputView.printMineField(mapToViewData(mineField))
    }

    private fun mapToViewData(mineField: MineField): List<List<Char>> {
        val result = MutableList(mineField.height.value) { MutableList(mineField.width.value) { EMPTY_VIEW } }

        for (cell in mineField.cells) {
            val (x, y) = cell.coordinate
            result[y][x] = if (cell is Cell.Mine) MINE_VIEW else EMPTY_VIEW
        }

        return result
    }

    companion object {
        const val MINE_VIEW = '*'
        const val EMPTY_VIEW = 'o'
    }
}
