package domain

data class Cell private constructor(val type: CellType) {
    companion object {
        fun ground(): Cell = Cell(CellType.GROUND)

        fun mine(): Cell = Cell(CellType.MINE)
    }
}

enum class CellType {
    GROUND, MINE
}
