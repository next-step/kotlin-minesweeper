package minesweeper.model

enum class Type(val symbol: String, val count: Int) {
    MINE("*", -1),
    ZERO("0", 0),
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);
}
