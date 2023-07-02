package minesweeper

import domain.CellType
import domain.MineCountNumber
import domain.MineSweeperMap
import domain.Position
import domain.PositiveNumber
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class MineSweeperMapTest {
    @Test
    fun `높이와 너비가 가지는 영역에 지뢰를 랜덤하게 위치시킨다`() {
        // [give]
        val height = PositiveNumber(3)
        val width = PositiveNumber(7)
        val mineCount = MineCountNumber(10, height, width)
        val mineMapProperty = MineSweeperMap.Property(height, width, mineCount)

        // [when]
        val mineSweeperMap = MineSweeperMap(mineMapProperty)

        // [then]
        val actualMineCount = mineSweeperMap.value.sumOf { row ->
            row.count { it.cellType == CellType.MINE }
        }

        actualMineCount shouldBe mineCount.value
    }

    @Test
    fun `특정 Cell의 위치에서 주변 8개 사각형에 포함된 유효한 좌표를 구할 수 있다`() {
        val height = PositiveNumber(4)
        val width = PositiveNumber(6)
        val mineCount = MineCountNumber(10, height, width)
        val mineMapProperty = MineSweeperMap.Property(height, width, mineCount)

        val position = Position(2, 2)
        val mineSweeperMap = MineSweeperMap(mineMapProperty)
        val baseCell = mineSweeperMap.getCellByPosition(position)

        val validPositions = baseCell.position.getValidPositionInRectangleArea(height, width)

        validPositions.value.forEach { it.shouldBeInstanceOf<Position>() }
        validPositions.value shouldBe listOf(
            Position(1, 1),
            Position(1, 2),
            Position(1, 3),
            Position(2, 1),
            Position(2, 3),
            Position(3, 1),
            Position(3, 2),
            Position(3, 3),
        )
    }
}
