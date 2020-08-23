package minesweeper.model

enum class Type(val symbol: String, val count: Int) {
    MINE("*", -1),
    EMPTY("0", 0),
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    companion object {
        private val TYPES: Map<Int, Type> = values().map { it.count }.associateWith { count -> values().filter { it.count == count }[0] }

        fun from(value: Int): Type {
            return TYPES.getOrElse(value) { throw IllegalArgumentException() }
        }
    }
}
