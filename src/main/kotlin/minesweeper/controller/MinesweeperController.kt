package minesweeper.controller

import minesweeper.adapter.MinesweeperInputAdapter
import minesweeper.domain.Field
import minesweeper.domain.FieldHeight
import minesweeper.domain.FieldInfo
import minesweeper.domain.FieldWidth
import minesweeper.domain.MineCount
import minesweeper.domain.MinePositionSelector
import minesweeper.domain.OpenResult
import minesweeper.domain.Position
import minesweeper.dto.FieldResponse
import minesweeper.view.OutputView

class MinesweeperController(
    private val inputAdapter: MinesweeperInputAdapter,
    private val outputView: OutputView,
    private val minePositionSelector: MinePositionSelector,
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

    fun makeNewField(
        fieldInfo: FieldInfo,
        mineCount: MineCount,
    ): Field {
        return Field(fieldInfo, minePositionSelector.generate(fieldInfo, mineCount))
    }

    fun playGame(field: Field) {
        outputView.printStartGameMessage()
        while (true) {
            if (doOpen(field)) return
        }
    }

    private fun doOpen(field: Field): Boolean {
        val openResult = field.openSpot(getOpenAttemptPosition())
        when (openResult) {
            is OpenResult.GameOver -> {
                outputView.printGameLoseMessage()
                return true
            }
            is OpenResult.Success -> {
                outputView.printField(FieldResponse(field))
            }
            OpenResult.AlreadyOpened -> {
                outputView.printAlreadyOpenedMessage(FieldResponse(field))
            }
        }
        return false
    }

    private fun getOpenAttemptPosition(): Position {
        return inputAdapter.fetchOpenAttemptPosition()
    }
}
