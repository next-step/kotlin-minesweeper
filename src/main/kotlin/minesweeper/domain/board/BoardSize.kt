package minesweeper.domain.board

class BoardSize(val width: Int, val height: Int) {

    companion object {
        fun of(width: Int, height: Int): BoardSize = BoardSize(width, height)
    }
}
