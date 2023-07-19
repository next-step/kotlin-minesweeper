package step4.domain

enum class CellType {
    UNKNOWN,
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    MINE,
    ;

    fun isMine(): Boolean = this == MINE
}
