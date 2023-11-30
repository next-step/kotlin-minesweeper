package domain

import inteface.MineCounter

class AdjacentMineCounter : MineCounter {
    override fun countMinesAround(board: Board, position: Position): Int {
        val neighborOffsets = listOf(
            Position(-1, -1), Position(0, -1), Position(1, -1),
            Position(-1, 0),  Position(1, 0),
            Position(-1, 1), Position(0, 1), Position(1, 1)
        )

        return neighborOffsets
            .map { offset -> Position(position.x + offset.x, position.y + offset.y) }
            .filter { it.isWithinBoardBounds(board.height, board.width) && it != position }
            .count { board.hasMineAt(it) }
    }

    private fun Position.isWithinBoardBounds(boardHeight: Int, boardWidth: Int): Boolean =
        x in 0 until boardWidth && y in 0 until boardHeight
}
