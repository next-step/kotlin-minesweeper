package domain

enum class CellType(
    val label: String,
) {
    MINE("X"),
    GENERAL("O"),
}
