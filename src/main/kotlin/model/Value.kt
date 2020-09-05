package model

enum class Value(val count: Int) {
    MINE(-99),
    UNDEFINE(-1),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    fun isMine(): Boolean = this == MINE

    fun addValue(): Value {
        if (this == MINE) return MINE
        return values().first { it.count == this.count + 1 }
    }

    override fun toString(): String {
        return return when (this.count) {
            in 0..8 -> this.count.toString()
            -1 -> "C"
            else -> "*"
        }
    }
}
