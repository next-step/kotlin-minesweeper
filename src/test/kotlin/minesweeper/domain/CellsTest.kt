package minesweeper.domain

import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import org.junit.jupiter.api.Test

class CellsTest {

    @Test
    fun `지뢰 매설이 잘된다`() {
        val cells = Cells(
            height = 5,
            width = 4,
        )


        cells.setMine(0, 0)
        cells.setMine(1, 1)

        cells.getCell(0, 0) should beInstanceOf(Mine::class)
        cells.getCell(1, 1) should beInstanceOf(Mine::class)
        cells.getMineCount() shouldBe 2
    }

    @Test
    fun `모든 땅이 열렸는지 확인한다`() {
        val cells = Cells(
            height = 2,
            width = 2,
        )

        cells.setMine(0, 0)

        cells.getCell(0, 1).open()
        cells.getCell(1, 0).open()
        cells.getCell(1, 1).open()

        cells.isAllLandOpened() shouldBe true
    }

    @Test
    fun `지뢰를 포함한 모든 셀을 연다`() {
        val cells = Cells(
            height = 2,
            width = 2,
        )

        cells.setMine(0, 0)

        cells.openAllCellsIncludeMines()

        cells.getCell(0, 0).isOpened shouldBe true
        cells.getCell(0, 1).isOpened shouldBe true
        cells.getCell(1, 0).isOpened shouldBe true
        cells.getCell(1, 1).isOpened shouldBe true
    }
}
