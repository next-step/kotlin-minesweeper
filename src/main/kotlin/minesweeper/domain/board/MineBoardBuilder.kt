package minesweeper.domain.board

import kotlin.properties.Delegates

class MineBoardBuilder {
    private var width: Int by Delegates.notNull()
    private var height: Int by Delegates.notNull()
    private var numberOfMines: Int by Delegates.notNull()

    fun width(value: Int) {
        width = value
    }

    fun height(value: Int) {
        height = value
    }

    fun numberOfMines(value: Int) {
        numberOfMines = value
    }

    fun build(): MineBoard {
        return MineBoard.of(width, height, numberOfMines)
    }
}
