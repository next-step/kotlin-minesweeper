package minesweeper.model.point

enum class Attribute(
    private val symbol: String,
) {
    MINE("*"),
    GROUND("C"),
}
