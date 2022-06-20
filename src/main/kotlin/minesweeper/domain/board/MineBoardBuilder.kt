package minesweeper.domain.board

import kotlin.properties.Delegates

class MineBoardBuilder {
    private var numberOfMines: Int by Delegates.notNull()
    private lateinit var board: Board
    private lateinit var mineStrategy: (numberOfCells: Int, numberOfMines: Int) -> List<Int>

    fun board(block: BoardBuilder.() -> Unit) {
        board = BoardBuilder().apply(block).build()
    }

    fun numberOfMines(value: Int) {
        numberOfMines = value
    }

    fun mineStrategy(strategy: (numberOfCells: Int, numberOfMines: Int) -> List<Int>) {
        mineStrategy = strategy
    }

    fun build() = MineBoard(board, numberOfMines, mineStrategy)
}
