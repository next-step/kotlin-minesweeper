package domain

enum class Cell(
    val symbol: String,
) {
    CLOSED("C"),
    MINE("*"),
    ;
}
