package minesweeper.domain

data class Mines(
    val list: List<Mine>
) {
    companion object {
        fun generateMine(num: Int, rows: Int, cols: Int): Mines {
            MapValidator.validate(rows, cols)
            return RandomLocationGenerator.location(num, rows, cols)
                .map { Mine(it.row, it.col) }
                .toMines()
        }
    }
}

private fun List<Mine>.toMines() = Mines(this)
