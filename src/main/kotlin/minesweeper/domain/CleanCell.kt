package minesweeper.domain

private const val CLEAN_SIGN = "C"

data class CleanCell(
    var nearMineCount: Int = 0,
) : Cell(CLEAN_SIGN) {
    fun nearMineIncrement() {
        nearMineCount++
    }
}

