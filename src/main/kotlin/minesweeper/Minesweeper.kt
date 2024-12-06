package minesweeper

class Minesweeper {
    companion object {
        fun setUp(height: Int, width: Int): Cells {
            return Cells.generate(height, width)
        }
    }
}