package minesweeper.model

object RandomMineFieldGenerator {
    private const val MIN_WIDTH = 1
    private const val MIN_HEIGHT = 1
    private const val MIN_MINE_COUNT = 1

    fun generate(width: Int, height: Int, mineCount: Int): List<Marks> {
        validate(width, height, mineCount)

        val mineMarks = List(mineCount) {
            Mark.mine()
        }

        val closeMarkCount = width * height - mineCount
        val closeMarks = List(closeMarkCount) {
            Mark.close()
        }

        val mergedMarks = mineMarks.plus(closeMarks)
            .shuffled()

        return List(height) {
            val startIndex = it * width
            val endIndex = startIndex + width
            Marks(mergedMarks.subList(startIndex, endIndex))
        }
    }

    private fun validate(width: Int, height: Int, mineCount: Int) {
        validateMinWidth(width)
        validateMinHeight(height)
        validateMineCount(width, height, mineCount)
    }

    private fun validateMinWidth(width: Int) = require(width >= MIN_WIDTH) { "너비는 최소 $MIN_WIDTH 이상이어야 합니다." }

    private fun validateMinHeight(height: Int) = require(height >= MIN_HEIGHT) { "높이는 최소 $MIN_HEIGHT 이상이어야 합니다." }

    private fun validateMineCount(width: Int, height: Int, mineCount: Int) {
        require(mineCount >= MIN_MINE_COUNT) { "지뢰 개수는 최소 ${MIN_MINE_COUNT}개 이상이어야 합니다." }

        val maxMineCount = width * height
        require(mineCount <= maxMineCount) { "지뢰 개수는 ${maxMineCount}개를 초과할 수 없습니다." }
    }
}
