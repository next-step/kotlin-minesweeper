package minesweeper.domain.board

import minesweeper.domain.common.PositiveInt
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

    fun build() = MineBoard(width, height, numberOfMines, mineStrategy)
}
