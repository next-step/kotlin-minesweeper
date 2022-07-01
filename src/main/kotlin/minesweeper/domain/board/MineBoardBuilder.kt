package minesweeper.domain.board

import minesweeper.domain.cell.CellMaker
import kotlin.properties.Delegates

class MineBoardBuilder {
    private var width: Int by Delegates.notNull()
    private var height: Int by Delegates.notNull()
    private var numberOfMines: Int by Delegates.notNull()
    private lateinit var cellMaker: CellMaker

    fun width(value: Int) {
        width = value
    }

    fun height(value: Int) {
        height = value
    }

    fun numberOfMines(value: Int) {
        numberOfMines = value
    }

    fun cellMaker(value: CellMaker) {
        cellMaker = value
    }

    fun build(): MineBoard {
        return MineBoard.of(width, height, numberOfMines, cellMaker)
    }
}
