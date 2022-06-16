package minesweeper.domain.board

import kotlin.properties.Delegates

class MineBoardBuilder {
    private var numberOfMines: Int by Delegates.notNull()
    private lateinit var board: Board

    fun board(block: BoardBuilder.() -> Unit) {
        board = BoardBuilder().apply(block).build()
    }

    fun numberOfMines(value: Int) {
        numberOfMines = value
    }

    fun build() = MineBoard(board, numberOfMines)
}
