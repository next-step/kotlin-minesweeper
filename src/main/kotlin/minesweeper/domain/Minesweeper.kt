package minesweeper.domain

import minesweeper.view.POSITION_SPLIT_SYMBOL

class Minesweeper(
    val positions: Positions,
    minePositions: MinePositions
) {
    private val minesweeperMineCount: Int

    init {
        minePositions.minePositions.forEach {
            positions.updatePositionValue(it.rowPosition, it.colPosition, -1)
        }

        val mineCount = MineCount(positions)
        mineCount.initMineCount()
        minesweeperMineCount = minePositions.mineCount
    }

    fun open(position: Pair<Int, Int>) {
        val (row, col) = position
        positions.open(row, col)
    }

    fun position(rows: Int, cols: Int): Position {
        return positions.position(rows, cols)
    }

    fun canPlayGame(position: Pair<Int, Int>): Boolean {
        return !(isGameWin() || isGameLose(position))
    }

    fun isGameWin(): Boolean {
        return positions.notOpenPositionCount() == minesweeperMineCount
    }

    private fun isGameLose(position: Pair<Int, Int>): Boolean {
        val (row, col) = position
        return positions.isMinePosition(row, col)
    }

    companion object {
        fun from(rows: Int, cols: Int, mine: Int): Minesweeper {
            val positionArray = Array(rows) {
                Array(cols) {
                    Position()
                }
            }

            return Minesweeper(
                Positions(positionArray),
                MinePositions.from(Rows(rows), Cols(cols), MineValue(mine, rows, cols))
            )
        }
    }
}
