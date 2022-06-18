package minesweeper

object RandomMineFieldGenerator {
    fun generate(width: Int, height: Int, mineCount: Int): List<Marks> {
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
}
