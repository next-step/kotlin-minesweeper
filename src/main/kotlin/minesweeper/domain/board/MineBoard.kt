package minesweeper.domain.board

import minesweeper.domain.common.NumberSet

class MineBoard private constructor(
    val mineIndices: NumberSet,
    val board: Board
) {
    companion object {
        fun of(mineIndices: NumberSet, board: Board): MineBoard {
            return MineBoard(mineIndices, board)
        }
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
