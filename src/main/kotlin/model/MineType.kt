package model

enum class MineType(val ascii: Int) {
    NONE(67),
    MINE(42),
    ZERO(48),
    ONE(49),
    TWO(50),
    THREE(51),
    FOUR(52),
    FIVE(53),
    SIX(54),
    SEVEN(55),
    EIGHT(56),
    NINE(578);

    companion object {
        fun findByAscii(ascii: Int): MineType = values().first { it.ascii == ascii }
    }
}
