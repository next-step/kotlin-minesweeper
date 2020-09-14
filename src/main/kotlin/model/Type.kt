package model

enum class Type(private val value: String) {
    MINE("*"),
    NONE("C"),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    fun isMine(): Boolean {
        return this == MINE
    }

    fun nextValue(): Type {
        return when (this) {
            ZERO -> ONE
            ONE -> TWO
            TWO -> THREE
            THREE -> FOUR
            FOUR -> FIVE
            FIVE -> SIX
            SIX -> SEVEN
            SEVEN -> EIGHT
            EIGHT -> EIGHT
            else -> this
        }
    }

    override fun toString(): String {
        return value
    }
}
