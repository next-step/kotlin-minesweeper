package minesweeper.domain.squares

import minesweeper.domain.square.Square

interface SquaresShufflingStrategy {
    fun shuffle(squares: List<Square>): List<Square>
}
