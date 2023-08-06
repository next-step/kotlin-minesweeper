package domain

data class Cell(
    val status: CellStatus
)

enum class CellStatus {
    EMPTY,
    MINE
}
