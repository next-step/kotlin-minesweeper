package minesweeper.controller

import minesweeper.adapter.MinesweeperInputAdapter
import minesweeper.domain.Field
import minesweeper.domain.FieldHeight
import minesweeper.domain.FieldWidth
import minesweeper.domain.MineCount
import minesweeper.dto.FieldResponse
import minesweeper.view.OutputView

class MinesweeperController(
    private val inputAdapter: MinesweeperInputAdapter,
    private val outputView: OutputView,
) {
    fun getFieldWidth(): FieldWidth {
        return inputAdapter.fetchFieldWidth()
    }

    fun getFieldHeight(): FieldHeight {
        return inputAdapter.fetchFieldHeight()
    }

    fun getMineCount(): MineCount {
        return inputAdapter.fetchMineCount()
    }

    fun announceInitialField(field: Field) {
        outputView.printInitialField(FieldResponse(field))
    }
}
