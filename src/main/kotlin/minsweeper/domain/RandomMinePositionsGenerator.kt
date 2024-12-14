package minsweeper.domain

class RandomMinePositionsGenerator : MinePositionsGenerator {

    override fun generate(area: Int, mineCount: Int): List<Int> {
        validateBoardSizeIsMoreThanMineCount(area, mineCount)
        return (0 until area).shuffled()
            .take(mineCount)
    }

    private fun validateBoardSizeIsMoreThanMineCount(area: Int, mineCount: Int) {
        require(mineCount <= area) {
            TOO_MANY_MINE_COUNT_EXCEPTION
        }
    }

    companion object {
        private const val TOO_MANY_MINE_COUNT_EXCEPTION = "지뢰 갯수가 높이 x 넓이를 초과할 수 없습니다"
    }

}
