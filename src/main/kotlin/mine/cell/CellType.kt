package mine.cell

sealed class CellType {

    abstract fun print(): String

    object MINE: CellType() {
        override fun print(): String {
            return PRINT_MINE
        }
    }

    object NONE: CellType() {
        override fun print(): String {
            return PRINT_NONE
        }
    }

    companion object {
        const val PRINT_MINE = "E"
        const val PRINT_NONE = "*"
    }
}
