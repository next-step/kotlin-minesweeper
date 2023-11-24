package enum

enum class CellStatus(val symbol: Char, val description: String) {
    MINE('*', "지뢰가 있는 칸"),
    EMPTY('C', "지뢰가 없는 칸")
}
