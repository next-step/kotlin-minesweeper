package minesweeper.domain.squares

import minesweeper.domain.square.Square

object RandomSquaresShufflingStrategy : SquaresShufflingStrategy {
    override fun shuffle(squares: List<Square>): List<Square> = squares.shuffled()
}
