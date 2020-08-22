package minesweeper.domain

private const val MINE_CHARACTER = "\uD83D\uDCA3"
private const val LOAD_CHARACTER = "\uD83D\uDFE9"
enum class CellType(val symbol: String) {
    MINE(MINE_CHARACTER),
    LOAD(LOAD_CHARACTER);
}
