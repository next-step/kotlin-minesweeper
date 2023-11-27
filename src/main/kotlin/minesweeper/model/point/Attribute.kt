package minesweeper.model.point

enum class Attribute(
    val symbol: String,
) {
    MINE("*"),
    FLAG("F"),
    NONE("C"),
}
