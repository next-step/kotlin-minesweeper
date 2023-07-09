package minesweeper

import domain.MineSweeperMap
import domain.PositiveNumber
import domain.position.Position
import domain.position.toPositions
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.TestMinePositionGenerator
import org.junit.jupiter.api.Test

class MineSweeperMapTest {
    @Test
    fun `특정 Cell의 위치에서 주변 8개 사각형에 포함된 유효한 좌표를 구할 수 있다`() {
        // [given]
        val height = PositiveNumber(4)
        val width = PositiveNumber(6)
        val mapProperty = MineSweeperMap.Property(height, width)

        val position = Position.of(2, 2)
        val mineSweeperMap = MineSweeperMap(mapProperty, TestMinePositionGenerator())
        val baseCell = mineSweeperMap.getCellByPosition(position)

        // [when]
        val validPositions = baseCell.position.getValidAdjacentPositions(height, width)

        // [then]
        validPositions.value.forEach { it.shouldBeInstanceOf<Position>() }
        validPositions shouldBe validPositionsOf(
            1 to 1,
            1 to 2,
            1 to 3,
            2 to 1,
            2 to 3,
            3 to 1,
            3 to 2,
            3 to 3,
        )
    }

    @Test
    fun `Close 상태의 Cell의 개수를 반환할 수 있다`() {
        // [given]
        val height = PositiveNumber(2)
        val width = PositiveNumber(2)
        val mapProperty = MineSweeperMap.Property(height, width)

        val position = Position.of(2, 2)
        val mineSweeperMap = MineSweeperMap(mapProperty, TestMinePositionGenerator(1 to 1))
        val cell = mineSweeperMap.getCellByPosition(position)

        // [when]
        cell.property.setOpen()
        val count = mineSweeperMap.getCloseCellCount()

        // [then]
        count shouldBe (height.value * width.value) - 1
    }

    @Test
    fun `Open 상태의 Mine Cell이 있는지 여부를 반환할 수 있다`() {
        // [given]
        val height = PositiveNumber(2)
        val width = PositiveNumber(2)
        val mapProperty = MineSweeperMap.Property(height, width)

        val position = Position.of(1, 1)
        val openMineSweeperMap = MineSweeperMap(mapProperty, TestMinePositionGenerator(1 to 1))
        val closeMineSweeperMap = MineSweeperMap(mapProperty, TestMinePositionGenerator(1 to 1))

        // [when]
        val mineCell = openMineSweeperMap.getCellByPosition(position)
        mineCell.property.setOpen()

        // [then]
        openMineSweeperMap.existsOpenMine() shouldBe true
        closeMineSweeperMap.existsOpenMine() shouldBe false
    }

    private fun validPositionsOf(vararg positionValues: Pair<Int, Int>) =
        positionValues.map { (row, column) -> Position.of(row, column) }.toPositions()
}
