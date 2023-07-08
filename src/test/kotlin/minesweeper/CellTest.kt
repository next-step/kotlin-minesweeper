package minesweeper

import domain.AroundMineCount
import domain.Cell
import domain.NormalCellProperty
import domain.Position
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `각 Cell은 open 여부를 구분할 수 있어야 한다`() {
        val position = Position.of(1, 1)
        val property = NormalCellProperty(AroundMineCount(0))
        val cell = Cell(position, property)

        cell.property.isOpen().shouldBeInstanceOf<Boolean>()
    }

    @Test
    fun `각 Cell은 주변 8개 좌표에 지뢰가 없는지 여부를 반환할 수 있어야 한다`() {
        val position = Position.of(1, 1)
        val cleanProperty = NormalCellProperty(AroundMineCount(0))
        val notCleanProperty = NormalCellProperty(AroundMineCount(1))

        val cleanCell = Cell(position, cleanProperty)
        val notCleanCell = Cell(position, notCleanProperty)

        cleanCell.property.isCleanAroundMineCount() shouldBe true
        notCleanCell.property.isCleanAroundMineCount() shouldBe false
    }
}
