package tdd.minesweeper.domain

data class Location(val row: Int, val col: Int) {
    fun toIndex(width: Int): Int = (row - 1) * width + (col - 1)

    fun isValid(area: Area): Boolean {
        return row in 1..area.height && col in 1..area.width
    }

    companion object {
        fun from(
            index: Int,
            width: Int,
        ): Location =
            Location(
                row = (index / width) + 1,
                col = (index % width) + 1,
            )
    }
}
