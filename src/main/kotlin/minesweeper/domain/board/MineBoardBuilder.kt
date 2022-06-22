package minesweeper.domain.board

import minesweeper.domain.board.strategy.MineStrategy
import minesweeper.domain.common.PositiveInt
import kotlin.properties.Delegates

class MineBoardBuilder {
    private var width: PositiveInt by Delegates.notNull()
    private var height: PositiveInt by Delegates.notNull()
    private var numberOfMines: PositiveInt by Delegates.notNull()
    private lateinit var mineStrategy: MineStrategy

    fun width(value: Int) {
        width = PositiveInt(value)
    }

    fun height(value: Int) {
        height = PositiveInt(value)
    }

    fun numberOfMines(value: Int) {
        numberOfMines = PositiveInt(value)
    }

    fun mineStrategy(mineStrategy: MineStrategy) {
        this.mineStrategy = mineStrategy
    }

    fun build(): MineBoard {
        return MineBoard.of(width, height, numberOfMines, mineStrategy)
    }
}
