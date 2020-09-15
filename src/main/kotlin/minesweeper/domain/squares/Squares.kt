package minesweeper.domain.squares

import minesweeper.domain.square.Position
import minesweeper.domain.square.Square
import minesweeper.domain.squarestate.Boundary
import minesweeper.domain.squarestate.Empty
import minesweeper.domain.squarestate.Mine
import minesweeper.domain.squarestate.SquareState

data class Squares private constructor(private val squares: List<Square>) {

    fun updateStateIfOnBoundary(height: Int, width: Int): Squares {
        val squaresOnBoundary: List<Square> = squares.filter { it.isOnBoundary(height, width) }
        val updatedSquares: List<Square> = this.updateStatesOf(squaresOnBoundary, newState = Boundary())
        return Squares(updatedSquares)
    }

    private fun updateStatesOf(squaresToChange: List<Square>, newState: SquareState): List<Square> =
        this.squares - squaresToChange + squaresToChange.map { it.copy(state = newState) }

    fun shuffled(squaresShuffleStrategy: SquaresShuffleStrategy): Squares =
        Squares(squaresShuffleStrategy.shuffle(squares))

    fun mineLaid(mineCount: Int): Squares {
        val squaresChosen: List<Square> = squares.filter { !it.isBoundary() }.take(mineCount)
        val updatedSquares: List<Square> = this.updateStatesOf(squaresChosen, newState = Mine())
        return Squares(updatedSquares.sortedByPosition())
    }

    private fun List<Square>.sortedByPosition(): List<Square> =
        this.sortedWith(compareBy({ it.position.x }, { it.position.y }))

    fun mineCounted(getMineCountOf: (Square) -> Int): Squares = Squares(
        squares.asSequence()
            .filter { !it.isBoundary() }
            .map { it.updateMineCount(getMineCountOf(it)) }.toList()
    )

    fun getMineCountOf(currentSquare: Square): Int =
        currentSquare.allAroundPositions().map { position ->
            this.squares.asSequence()
                .filter { it.hasSamePosition(position) }
                .count { it.isMine() }
        }.sumBy { it }

    operator fun get(x: Int, y: Int): Square = squares.firstOrNull { it.hasSamePosition(Position(x, y)) }
        ?: throw IllegalArgumentException("Invalid Position: ($x, $y)")

    fun joinToString(): String {
        val lastIndexOfRow: Int = squares.last().position.y
        return squares.joinToString("") { square ->
            when (square.position.y) {
                lastIndexOfRow -> "${square.currentState()}\n"
                else -> "${square.currentState()} "
            }
        }
    }

    companion object {
        fun createAllWithBoundary(height: Int, width: Int): Squares = Squares(
            (0..(height + 1)).flatMap { x ->
                (0..(width + 1)).map { y -> Square(x, y, state = Empty.default) }
            }
        )

        fun createFrom(vararg square: Square): Squares = Squares(square.toList())
    }
}
