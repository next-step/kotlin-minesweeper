package minesweeper.domain

import minesweeper.domain.button.ButtonGraph
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class MineSweeperGame(
    val buttonGraph: ButtonGraph
) {
    val height: Int = buttonGraph.height

    val width: Int = buttonGraph.width

    val totalMineCount: Int = buttonGraph.totalMineCount

    init {
        require(buttonGraph.isNotEmpty()) {
            "Buttons should not be empty"
        }
    }

    companion object {
        fun of(height: Int, width: Int, totalMineCount: Int, vararg minePosition: Position): MineSweeperGame =
            MineSweeperGame(
                ButtonGraph.of(height, width, totalMineCount, Positions(minePosition.toList()))
            )

        fun of(height: Int, width: Int, totalMineCount: Int, minePositions: Positions): MineSweeperGame =
            MineSweeperGame(
                ButtonGraph.of(height, width, totalMineCount, minePositions)
            )
    }
}
