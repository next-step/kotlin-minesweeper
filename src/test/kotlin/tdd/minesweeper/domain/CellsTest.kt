package tdd.minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import tdd.helper.FixedMinePlaceStrategy

class CellsTest {

    @Test
    fun `높이와 너비로 Cells를 만든다`() {
        assertDoesNotThrow { Cells(3, 3, 2) }
    }

    @Test
    fun `Cells이 만들어지면 지뢰가 정상적으로 심어진다`() {
        val cells = Cells(3, 3, 2)
        cells.getActiveMineCount() shouldBe 2
    }

    @Test
    fun `땅은 주변 지뢰 개수를 표시한다`() {
        val cells = Cells(
            height = 3,
            width = 3,
            mineCount = 2,
            minePlaceStrategy = FixedMinePlaceStrategy(
                listOf(
                    0 to 0,
                    1 to 1,
                ),
            ),
        )

        (cells.getCell(0, 1) as Land).adjacentMineCount shouldBe 2
        (cells.getCell(0, 2) as Land).adjacentMineCount shouldBe 1
        (cells.getCell(1, 0) as Land).adjacentMineCount shouldBe 2
        (cells.getCell(1, 2) as Land).adjacentMineCount shouldBe 1
        (cells.getCell(2, 0) as Land).adjacentMineCount shouldBe 1
        (cells.getCell(2, 1) as Land).adjacentMineCount shouldBe 1
        (cells.getCell(2, 2) as Land).adjacentMineCount shouldBe 1
    }

    @Test
    fun `지뢰를 열면 false를 반환한다`() {
        val cells = Cells(
            height = 3,
            width = 3,
            mineCount = 2,
            minePlaceStrategy = FixedMinePlaceStrategy(
                listOf(
                    0 to 0,
                    1 to 1,
                ),
            ),
        )

        cells.openCellAndAdjacentCells(0, 0) shouldBe false
    }

    @Test
    fun `지뢰가 아닌 셀을 열면 주변 지뢰가 없다면 주변 셀을 열어준다`() {
        val cells = Cells(
            height = 4,
            width = 4,
            mineCount = 2,
            minePlaceStrategy = FixedMinePlaceStrategy(
                listOf(
                    1 to 3,
                    2 to 2,
                ),
            ),
        )

        cells.openCellAndAdjacentCells(0, 0) shouldBe true
        cells.getCell(0, 0).isOpened() shouldBe true
        cells.getCell(0, 1).isOpened() shouldBe true
        cells.getCell(1, 0).isOpened() shouldBe true
        cells.getCell(3, 3).isOpened() shouldBe false
    }
}
