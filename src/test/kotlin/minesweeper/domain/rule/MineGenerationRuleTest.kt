package minesweeper.domain.rule

import io.kotest.matchers.shouldBe
import minesweeper.domain.BoardMetadata
import minesweeper.domain.CellType
import minesweeper.domain.Coordinate
import org.junit.jupiter.api.Test

class MineGenerationRuleTest {

    @Test
    fun `랜덤 생성 - 게임판 생성 규칙은 게임판의 높이,넓이, 그리고 지뢰의 개수를 갖고 생성한다`() {
        val rule = RandomMineGenerationRule()
        val rawBoard = rule.generate(BoardMetadata(10, 10, 10))

        rawBoard.keys.size shouldBe 100
        rawBoard.values.filter { it.type == CellType.MINE }.size shouldBe 10
    }

    @Test
    fun `랜덤 생성 - 게임판 최소 크기 생성`() {
        val rule = RandomMineGenerationRule()
        val rawBoard = rule.generate(BoardMetadata(1, 1, 1))

        rawBoard.keys.size shouldBe 1
        rawBoard.values.filter { it.type == CellType.MINE }.size shouldBe 1
    }

    @Test
    fun `지뢰의 위치를 직접 좌표로 지정해서 생성`() {
        val mineCoordinates = listOf(
            Coordinate(0, 0),
            Coordinate(0, 1),
            Coordinate(1, 0)
        )
        val numOfMine = mineCoordinates.size
        val rule = TestMineGenerationRule(mineCoordinates)
        val rawBoard = rule.generate(BoardMetadata(4, 5, numOfMine))

        rawBoard.keys.size shouldBe 20
        rawBoard.values.filter { it.type == CellType.MINE }.size shouldBe numOfMine
        rawBoard[Coordinate(0, 0)]!!.type shouldBe CellType.MINE
        rawBoard[Coordinate(0, 1)]!!.type shouldBe CellType.MINE
        rawBoard[Coordinate(1, 0)]!!.type shouldBe CellType.MINE
    }
}
