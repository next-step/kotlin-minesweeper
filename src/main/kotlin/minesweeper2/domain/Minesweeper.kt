package minesweeper2.domain

import minesweeper2.model.PositionLocation

class Minesweeper(
    val positions: Positions,
) {

    init {
        val mineCount = MineCount(positions)
        mineCount.initMineCount()
    }

    fun open(position: PositionLocation) {
        positions.open(position)
    }

    fun position(rows: Int, cols: Int): Position {
        return positions.position(rows, cols)
    }

    fun canPlayGame(position: PositionLocation): Boolean {
        return !(isGameWin() || isGameLose(position))
    }

    fun isGameWin(): Boolean {
        return positions.notOpenPositionCount() == positions.mineCount()
    }

    private fun isGameLose(position: PositionLocation): Boolean {
        val (row, col) = position
        return positions.isMinePosition(row, col)
    }

    companion object {
        fun from(rows: Int, cols: Int, mine: Int): Minesweeper {
            return Minesweeper(Positions.from(Row(rows), Col(cols), Mine(mine, rows, cols)))
        }
    }
}
