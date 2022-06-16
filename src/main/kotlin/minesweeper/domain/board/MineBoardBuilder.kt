package minesweeper.domain.board

import kotlin.properties.Delegates

class MineBoardBuilder {
    private var numberOfMines: Int by Delegates.notNull()
    private lateinit var board: Board
    private lateinit var mineIndices: List<Int>

    fun board(block: BoardBuilder.() -> Unit) {
        board = BoardBuilder().apply(block).build()
    }

    fun numberOfMines(value: Int) {
        numberOfMines = value
    }

    fun mineStrategy(strategy: (numberOfCells: Int, numberOfMines: Int) -> List<Int>) {
        require(numberOfMines in (0..board.size)) { "number of mines must be within range of 0 ~ ${board.size}" }
        mineIndices = strategy(board.size, numberOfMines)
    }

    fun build() = MineBoard(board, mineIndices)
}
