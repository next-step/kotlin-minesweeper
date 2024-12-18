package minesweeper

sealed class CellType {
    data object Mine : CellType()

    data class Number(val mineCount: Int) : CellType()

    fun isMine(): Boolean {
        return this is Mine
    }

    fun mineCount(): Int {
        return when (this) {
            is Mine -> 0
            is Number -> mineCount
        }
    }

    companion object {
        val DEFAULT: CellType = Number(0)

        fun fromMineCount(mineCount: Int): CellType {
            return Number(mineCount)
        }
    }
}
