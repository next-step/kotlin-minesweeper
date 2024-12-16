package minsweeper.domain

class Board private constructor(val boardLines: BoardLines) {

    companion object {

        fun of(
            boardSize: BoardSize,
            mineCount: Int,
            boardLinesGenerator: BoardLinesGenerator,
        ): Board = Board(boardLinesGenerator.generate(boardSize, mineCount))

    }

}
