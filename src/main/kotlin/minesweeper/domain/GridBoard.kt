package minesweeper.domain

import minesweeper.domain.squares.Squares
import minesweeper.domain.squares.SquaresShufflingStrategy

class GridBoard(private val squares: Squares) {

    constructor(height: Int, width: Int) : this(
        Squares.createAllWithinBoundaries(height, width)
            .updateStateIfOnBoundary(height, width)
    )

    fun mineLaid(mineCount: Int, squaresShufflingStrategy: SquaresShufflingStrategy): GridBoard {
        val shuffled: Squares = squares.shuffled(squaresShufflingStrategy)
        val mineLaid: Squares = shuffled.mineLaid(mineCount)
        return GridBoard(mineLaid)
    }

    fun mineCounted(): GridBoard {
        val mineCounted: Squares = squares.mineCounted { squares.getMineCountOf(it) }
        return GridBoard(mineCounted)
    }

    fun joinToString(): String = squares.joinToString()
}
