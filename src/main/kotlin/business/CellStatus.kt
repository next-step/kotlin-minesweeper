package business

enum class CellStatus {
    MINE, EMPTY;

    fun isMine(): Boolean = this == MINE
}
