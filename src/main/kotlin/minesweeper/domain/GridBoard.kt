package minesweeper.domain

import minesweeper.domain.squares.Squares
import minesweeper.domain.squares.SquaresShuffleStrategy

class GridBoard(private val squares: Squares) {

    constructor(height: Int, width: Int) : this(
        Squares.createAllWithBoundary(height, width)
            .updateStateIfOnBoundary(height, width)
    )

    fun mineLaid(mineCount: Int, squaresShuffleStrategy: SquaresShuffleStrategy): GridBoard {
        val shuffled: Squares = squares.shuffled(squaresShuffleStrategy)
        val mineLaid: Squares = shuffled.mineLaid(mineCount)
        return GridBoard(mineLaid)
    }

    fun mineCounted(): GridBoard {
        val mineCounted: Squares = squares.mineCounted { squares.getMineCountOf(it) }
        return GridBoard(mineCounted)
    }

    fun joinToString(): String = squares.joinToString()
}
