package domain

enum class CellType {
    MINE, NORMAL,
    ;

    companion object {
        private const val CLOSE_SYMBOL = "C"
        private const val OPEN_MINE_SYMBOL = "*"

        fun symbolFromIsOpen(isOpen: Boolean, openSymbol: String = OPEN_MINE_SYMBOL): String {
            return when (isOpen) {
                true -> openSymbol
                false -> CLOSE_SYMBOL
            }
        }
    }
}
