package minesweeper.domain.rule

import io.kotest.matchers.shouldBe
import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell
import org.junit.jupiter.api.Test

class BoardGenerationRuleTest {

    @Test
    fun `랜덤 생성 - 게임판 생성 규칙은 게임판의 높이,넓이, 그리고 지뢰의 개수를 갖고 생성한다`() {
        val rule = RandomBoardGenerationRule()
        val rawBoard = rule.generate(BoardMetadata(10, 10, 10))

        for (row in rawBoard) {
            for (cell in row) {
                print("$cell ")
            }
            println()
        }

        rawBoard.size shouldBe 10
        rawBoard[0].size shouldBe 10
    }

    @Test
    fun `지뢰의 위치를 직접 좌표로 지정해서 생성`() {
        val mineCoordinates = listOf(0 to 0, 0 to 1, 1 to 0)
        val numOfMine = mineCoordinates.size
        val rule = TestBoardGenerationRule(mineCoordinates)
        val rawBoard = rule.generate(BoardMetadata(4, 5, numOfMine))

        rawBoard.size shouldBe 4
        rawBoard[0].size shouldBe 5
        rawBoard[0][0] shouldBe Cell.MINE
        rawBoard[0][1] shouldBe Cell.MINE
        rawBoard[1][0] shouldBe Cell.MINE
    }
}
