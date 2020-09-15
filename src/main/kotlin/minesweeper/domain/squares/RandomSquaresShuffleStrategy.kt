package minesweeper.domain.squares

import minesweeper.domain.square.Square

object RandomSquaresShuffleStrategy : SquaresShuffleStrategy {
    override fun shuffle(squares: List<Square>): List<Square> = squares.shuffled()
}
