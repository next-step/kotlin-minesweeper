package domain

data class Cell private constructor(val type: CellType, val position: Position) {
    companion object {
        fun ground(x: Int, y: Int): Cell = Cell(CellType.GROUND, position(x, y))

        fun mine(x: Int, y: Int): Cell = Cell(CellType.MINE, position(x, y))

        private fun position(x: Int, y: Int): Position = Position(x, y)
    }
}

enum class CellType {
    GROUND, MINE
}
