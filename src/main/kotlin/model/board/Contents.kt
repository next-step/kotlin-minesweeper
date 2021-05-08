package model.board

enum class Contents(val mineCount: Int) {
    MINE(9),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    companion object {
        fun mineCountOf(mineCount: Int): Contents? {
            return Contents.values().find { it.mineCount == mineCount }
        }
    }
}
