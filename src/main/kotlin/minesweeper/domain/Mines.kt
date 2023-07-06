package minesweeper.domain

data class Mines(
    val list: List<Mine>
) {
    companion object {
        fun generateMine(mineNumber: Int, size: Size): Mines {
            require(mineNumber < size.row * size.col) { "mine의 수는 cell의 수보다 작거나 같아야 됩니다." }
            return RandomLocationGenerator.location(mineNumber, size.row, size.col)
                .map { Mine(it.row, it.col) }
                .toMines()
        }
    }
}

private fun List<Mine>.toMines() = Mines(this)
