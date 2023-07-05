package minesweeper.domain

data class Mines(
    val list: List<Mine>
) {
    companion object {
        fun generateMine(num: Int, rows: Int, cols: Int): Mines {
            return RandomLocationGenerator.location(num, rows, cols)
                .map { Mine(it.first, it.second) }
                .toMines()
        }
    }
}

private fun List<Mine>.toMines() = Mines(this)
