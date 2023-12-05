package minesweeper.model.point

enum class Attribute {
    MINE,
    GROUND, ;

    fun isMine(): Boolean {
        return this == MINE
    }
}
