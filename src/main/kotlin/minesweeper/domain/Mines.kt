package minesweeper.domain

data class Mines(
    val list: List<Mine>
) {
    companion object {
        fun generateMine(num: Int, size: Size): Mines {
            return RandomLocationGenerator.location(num, size.row, size.col)
                .map { Mine(it.row, it.col) }
                .toMines()
        }
    }
}

private fun List<Mine>.toMines() = Mines(this)
