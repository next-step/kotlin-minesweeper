package minesweeper.domain.field

enum class Mark {
    MINE,
    SAFE,
    ;

    fun isMine(): Boolean {
        return this == MINE
    }
}
