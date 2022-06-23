package minesweeper.domain.board

import kotlin.properties.Delegates

class MineBoardBuilder {
    private var width: Int by Delegates.notNull()
    private var height: Int by Delegates.notNull()
    private var numberOfMines: Int by Delegates.notNull()
    private lateinit var mineMaker: MineMaker

    fun width(value: Int) {
        width = value
    }

    fun height(value: Int) {
        height = value
    }

    fun numberOfMines(value: Int) {
        numberOfMines = value
    }

    fun mineStrategy(mineMaker: MineMaker) {
        this.mineMaker = mineMaker
    }

    fun build(): MineBoard {
        return MineBoard.of(width, height, numberOfMines, mineMaker)
    }
}
