package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.position.Positions

@JvmInline
value class Board(val cells: Cells) {

    companion object {
        fun of(boardSize: BoardSize, mineCount: Int): Board {
            val positions = Positions.of(boardSize)
            val randomPositions = positions.getRandomPositions(mineCount)
            return Board(Cells.of(positions, randomPositions))
        }
    }
}
