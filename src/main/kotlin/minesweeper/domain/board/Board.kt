package minesweeper.domain.board

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
