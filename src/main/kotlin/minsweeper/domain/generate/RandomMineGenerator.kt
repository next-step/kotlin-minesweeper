package minsweeper.domain.generate

import minsweeper.domain.BoardSize
import minsweeper.domain.Coordinate

class RandomMineGenerator : MineGenerator {

    override fun generate(
        boardSize: BoardSize,
        mineAmount: Int,
    ): List<Coordinate> {
        require(mineAmount <= boardSize.height * boardSize.width) {
            MINE_AMOUNT_EXCEED_BOARD_CELLS_AMOUNT
        }

        return List(boardSize.height) { row ->
            List(boardSize.width) { column -> Coordinate.of(row + 1, column + 1) }
        }.flatten()
            .toMutableList()
            .apply { shuffle() }
            .take(mineAmount)
    }

    companion object {
        private const val MINE_AMOUNT_EXCEED_BOARD_CELLS_AMOUNT = "지뢰 갯수는 판의 셀 갯수보다 클 수 없습니다"
    }

}