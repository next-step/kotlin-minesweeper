package model

enum class Value(val string: String) {
    MINE("*"),
    UNDEFINE("C"),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    companion object {
        fun plusValue(value: Value): Value {
            if (value == MINE) return MINE
            return values()[value.ordinal + 1]
        }
    }
}
