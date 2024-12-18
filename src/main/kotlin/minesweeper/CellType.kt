package minesweeper

sealed class CellType {
    data object Mine : CellType()
    data class Number(val mineCount: Int) : CellType()

    fun isMine(): Boolean {
        return this is Mine
    }

    companion object {
        val EMPTY: CellType = Number(0)

        fun fromMineCount(mineCount: Int): CellType {
            return Number(mineCount)
        }
    }
}
