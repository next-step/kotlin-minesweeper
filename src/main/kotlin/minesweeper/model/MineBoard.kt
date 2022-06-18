package minesweeper.model

class MineBoard private constructor(
    val board: List<Cells>
) {

    override fun toString() = board.joinToString(CELLS_SEPARATOR)

    companion object {
        private const val MIN_WIDTH = 1
        private const val MIN_HEIGHT = 1
        private const val MIN_MINE_COUNT = 1
        private const val CELLS_SEPARATOR = "\n"

        fun of(width: Int, height: Int, mineCount: Int): MineBoard {
            validate(width, height, mineCount)

            val mineCells = List(mineCount) {
                Cell.mine()
            }

            val closeCellCount = width * height - mineCount
            val closeCells = List(closeCellCount) {
                Cell.close()
            }

            val mergedCells = mineCells.plus(closeCells)
                .shuffled()

            val board = List(height) {
                val startIndex = it * width
                val endIndex = startIndex + width
                Cells(mergedCells.subList(startIndex, endIndex))
            }

            return MineBoard(board)
        }

        private fun validate(width: Int, height: Int, mineCount: Int) {
            validateMinWidth(width)
            validateMinHeight(height)
            validateMineCount(width, height, mineCount)
        }

        private fun validateMinWidth(width: Int) =
            require(width >= MIN_WIDTH) { "너비는 최소 $MIN_WIDTH 이상이어야 합니다." }

        private fun validateMinHeight(height: Int) =
            require(height >= MIN_HEIGHT) { "높이는 최소 $MIN_HEIGHT 이상이어야 합니다." }

        private fun validateMineCount(width: Int, height: Int, mineCount: Int) {
            require(mineCount >= MIN_MINE_COUNT) { "지뢰 개수는 최소 ${MIN_MINE_COUNT}개 이상이어야 합니다." }

            val maxMineCount = width * height
            require(mineCount <= maxMineCount) { "지뢰 개수는 ${maxMineCount}개를 초과할 수 없습니다." }
        }
    }
}
