package minesweeper.domain

class Minesweeper(dimension: Dimension, mineCount: PositiveNumber) {

    private val board: Board

    init {
        require(dimension.size() >= mineCount) {
            "지뢰 갯수는 전체 블럭 넓이 보다 많을 수 없습니다."
        }

        board = Board(dimension, RandomMineFactory(dimension, mineCount))
    }

    fun allBlocks(): List<List<Block>> {
        return board.allBlocks()
    }
}
