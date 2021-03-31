package dto

import domain.MineBoard
import domain.block.Block

data class MineBoardDto(val board: List<List<Char>>) {
    constructor(mineBoard: MineBoard) : this(mineBoard.toView())
}

private fun MineBoard.toView(): List<List<Char>> {
    val board = this.value
    val width = this.width

    return board.toSortedMap()
        .map { it.value }
        .map { it.toView() }
        .windowed(size = width, step = width)
}

private fun Block.toView(): Char {
    return when (this.isMine()) {
        true -> '■'
        false -> '0'.plus(this.surroundingMineCount().value)
    }
}
