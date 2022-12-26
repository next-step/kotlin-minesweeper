package minesweeper.domain

data class Position(val height: Int, val width: Int) {
    fun rangeIn(
        maxHeight: Int,
        maxWidth: Int,
    ): Boolean = this.height in 0 until maxHeight && this.width in 0 until maxWidth

    fun getNearPositions(): List<Position> =
        (this.height - 1..this.height + 1).flatMap { height ->
            (this.width - 1..this.width + 1).map { width ->
                Position(height, width)
            }
        }
}


