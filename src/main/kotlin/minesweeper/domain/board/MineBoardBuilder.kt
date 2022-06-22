package minesweeper.domain.board

import minesweeper.domain.board.strategy.MineStrategy
import kotlin.properties.Delegates

class MineBoardBuilder {
    private var width: Int by Delegates.notNull()
    private var height: Int by Delegates.notNull()
    private var numberOfMines: Int by Delegates.notNull()
    private lateinit var mineStrategy: MineStrategy

    fun width(value: Int) {
        width = value
    }

    fun height(value: Int) {
        height = value
    }

    fun numberOfMines(value: Int) {
        numberOfMines = value
    }

    fun mineStrategy(mineStrategy: MineStrategy) {
        this.mineStrategy = mineStrategy
    }

    fun build(): MineBoard {
        return MineBoard(width, height, numberOfMines, mineStrategy)
    }
}
