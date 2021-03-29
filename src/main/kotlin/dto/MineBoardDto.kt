package dto

import domain.CoordinateNotFoundException
import domain.MineBoard
import domain.block.Block

data class MineBoardDto(val board: List<List<Char>>) {
    constructor(mineBoard: MineBoard) : this(mineBoard.toView())
}

private fun MineBoard.toView(): List<List<Char>> {
    val board = this.value
    val width = board.keys.map { it.x }.max() ?: throw CoordinateNotFoundException("좌표가 존재하지 않습니다. coordinates: ${board.keys}")

    return board.toSortedMap()
        .map { it.value }
        .map { it.toView() }
        .windowed(size = width, step = width)
}

private fun Block.toView(): Char {
    return when (this.isMine()) {
        true -> '■'
        false -> '□'
    }
}
