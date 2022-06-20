package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Empty
import minesweeper.domain.cell.Mine
import minesweeper.domain.common.NumberSet
import minesweeper.domain.common.Position
import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.contains
import minesweeper.domain.common.div
import minesweeper.domain.common.rangeTo
import minesweeper.domain.common.rem
import kotlin.properties.Delegates

class MineBoardBuilder {
    private var width: PositiveInt by Delegates.notNull()
    private var height: PositiveInt by Delegates.notNull()
    private var numberOfMines: PositiveInt by Delegates.notNull()
    private lateinit var mineStrategy: (numberOfCells: PositiveInt, numberOfMines: PositiveInt) -> List<Int>

    fun width(value: Int) {
        width = PositiveInt(value)
    }

    fun height(value: Int) {
        height = PositiveInt(value)
    }

    fun numberOfMines(value: Int) {
        numberOfMines = PositiveInt(value)
    }

    fun mineStrategy(strategy: (numberOfCells: PositiveInt, numberOfMines: PositiveInt) -> List<Int>) {
        mineStrategy = strategy
    }

    fun build(): MineBoard {
        val size = width * height

        require(numberOfMines in (0..size)) { "number of mines must be within range of 0 ~ $size" }

        val mineIndices = NumberSet.of(mineStrategy(size, numberOfMines))
        val board = createBoard(width, height, mineIndices)

        val mineBoard = MineBoard.of(mineIndices, board)
        NearbyMineCounter.count(mineBoard)
        return mineBoard
    }

    private fun createBoard(width: PositiveInt, height: PositiveInt, mineIndices: NumberSet): Board {
        var mine = 0
        var empty = 0

        val size = (width * height).value
        val cells = Cells(
            List(size) {
                val x = it % width
                val y = it / width
                if (it in mineIndices) {
                    mine += 1
                    Mine(Position.of(x, y))
                } else {
                    empty += 1
                    Empty(Position.of(x, y))
                }
            }
        )
        return Board.of(width, height, cells)
    }
}
