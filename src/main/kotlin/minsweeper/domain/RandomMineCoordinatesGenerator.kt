package minsweeper.domain

class RandomMineCoordinatesGenerator : MineCoordinatesGenerator {

    override fun generate(size: BoardSize, mineCount: Int): List<Coordinate> {
        val area = size.height * size.width
        validateBoardSizeIsMoreThanMineCount(area, mineCount)
        return (0 until area).shuffled()
            .take(mineCount)
            .map {
                val row = it / size.width
                val column = it % size.width
                return@map Coordinate(row, column)
            }
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
