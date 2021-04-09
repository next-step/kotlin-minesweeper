package model

import model.board.BoardSize

class RandomPositionsBuilder {
    fun build(boardSize: BoardSize, size: Int): Positions {
        return Positions(
            List(boardSize.height) { it }.flatMap { height ->
                List(boardSize.width) { it }.shuffled().map { width ->
                    Position.get(height, width)
                }
            }.shuffled().take(size)
        )
    }
}
