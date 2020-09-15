package minesweeper.domain.squares

import minesweeper.domain.square.Square

interface SquaresShuffleStrategy {
    fun shuffle(squares: List<Square>): List<Square>
}
