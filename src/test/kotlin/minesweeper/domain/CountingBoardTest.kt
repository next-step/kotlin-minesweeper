package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.rule.TestMineGenerationRule
import org.junit.jupiter.api.Test

class CountingBoardTest {
    /*
    * 2 1 1 0
    1 2 * 2 1
    2 3 2 2 *
    * * 1 1 1
    2 2 1 0 0
     */
    @Test
    fun `게임판은 지뢰를 생성한 뒤, 주변 지뢰 개수를 계산하여 갖고 있다`() {
        val mineCoordinates = listOf(
            Coordinate(0, 0),
            Coordinate(1, 2),
            Coordinate(2, 4),
            Coordinate(3, 0),
            Coordinate(3, 1)
        )
        val numOfMine = mineCoordinates.size
        val board = Board(
            BoardMetadata(5, 5, numOfMine),
            TestMineGenerationRule(mineCoordinates)
        )
        val countingBoard = CountingBoard(board)

        mineCoordinates.forEach {
            board.at(it.row, it.col).shouldBeInstanceOf<MineCell>()
        }
        countingBoard.countAroundMine(0, 1) shouldBe 2
        countingBoard.countAroundMine(0, 2) shouldBe 1
        countingBoard.countAroundMine(0, 3) shouldBe 1
        countingBoard.countAroundMine(0, 4) shouldBe 0
        countingBoard.countAroundMine(1, 0) shouldBe 1
        countingBoard.countAroundMine(1, 1) shouldBe 2
        countingBoard.countAroundMine(1, 3) shouldBe 2
        countingBoard.countAroundMine(1, 4) shouldBe 1
        countingBoard.countAroundMine(2, 0) shouldBe 2
        countingBoard.countAroundMine(2, 1) shouldBe 3
        countingBoard.countAroundMine(2, 2) shouldBe 2
        countingBoard.countAroundMine(2, 3) shouldBe 2
        countingBoard.countAroundMine(3, 2) shouldBe 1
        countingBoard.countAroundMine(3, 3) shouldBe 1
        countingBoard.countAroundMine(3, 4) shouldBe 1
        countingBoard.countAroundMine(4, 0) shouldBe 2
        countingBoard.countAroundMine(4, 1) shouldBe 2
        countingBoard.countAroundMine(4, 2) shouldBe 1
        countingBoard.countAroundMine(4, 3) shouldBe 0
        countingBoard.countAroundMine(4, 4) shouldBe 0
    }

}