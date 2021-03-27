package dto

import domain.Block
import domain.MineBoard

data class MineBoardDto(val board: List<List<Char>>) {
    constructor(mineBoard: MineBoard) : this(toto(mineBoard))

    companion object {
        private fun toto(mineBoard: MineBoard): List<List<Char>> {
            val board = mineBoard.value
            val width = board.keys.maxBy { it.x }!!.x

            return board.toSortedMap()
                .map { it.value }
                .map { it.toView() }
                .windowed(size = width, step = width)
        }
    }
}

private fun Block.toView(): Char {
    return when (this) {
        Block.MINE -> '■'
        Block.NOTHING -> '□'
    }
}
