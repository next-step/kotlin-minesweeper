package minesweeper.controller

import minesweeper.adapter.MinesweeperInputAdapter
import minesweeper.domain.Field
import minesweeper.domain.FieldHeight
import minesweeper.domain.FieldInfo
import minesweeper.domain.FieldWidth
import minesweeper.domain.MineCount
import minesweeper.domain.MinePositionSelector
import minesweeper.domain.MinesweeperGame
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

    fun makeNewGame(
        fieldInfo: FieldInfo,
        mineCount: MineCount,
    ): MinesweeperGame {
        val field = Field(fieldInfo, minePositionSelector.generate(fieldInfo, mineCount))
        return MinesweeperGame(field)
    }

    fun playGame(minesweeperGame: MinesweeperGame) {
        outputView.printStartGameMessage()
        while (!minesweeperGame.isFinished) {
            val openAttemptPosition = getOpenAttemptPosition()
            minesweeperGame.openSpot(openAttemptPosition)
            if (minesweeperGame.isFinished) {
                outputView.printGameLoseMessage()
                return
            }
            outputView.printField(FieldResponse(minesweeperGame.field))
        }
    }

    private fun getOpenAttemptPosition(): Position {
        return inputAdapter.fetchOpenAttemptPosition()
    }
}
