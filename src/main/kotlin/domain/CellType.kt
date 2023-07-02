package domain

enum class CellType {
    MINE, NORMAL,
    ;
    companion object {
        const val MINE_SYMBOL = "*"
    }
}
