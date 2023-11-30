package minesweeper.domain

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineMapTest {
    @Test
    fun `위치 정보를 전달해 MineMap에 지뢰를 심는다`() {
        // given
        val mineMap = MineMap()

        // when
        mineMap.plantCell(Position(1, 1), Cell(CellState.MINE))

        // then
        assertEquals(1, mineMap.size)
        assertEquals(CellState.MINE, mineMap.values[Position(1, 1)]?.state)
    }

    @Test
    fun `위치 정보를 전달해 MineMap에 빈 상태를 심는다`() {
        // given
        val mineMap = MineMap()

        // when
        mineMap.plantCell(Position(1, 1), Cell(CellState.EMPTY))
        mineMap.plantCell(Position(2, 2), Cell(CellState.EMPTY))

        // then
        assertEquals(2, mineMap.size)
        assertEquals(CellState.EMPTY, mineMap.values[Position(1, 1)]?.state)
        assertEquals(CellState.EMPTY, mineMap.values[Position(2, 2)]?.state)
    }

    @Test
    fun `주어진 위치 정보에 놓인 Cell 객체를 가져온다`() {
        // given
        val mineMap = MineMap()
        val position = Position(1, 1)
        mineMap.plantCell(position, Cell(CellState.EMPTY))

        // when
        val cell = mineMap.getCell(position)

        // then
        assertEquals(CellState.EMPTY, cell.state)
    }

    @Test
    fun `MineMap에 없는 위치 정보를 통해 Cell 객체를 가져온다면 IllegalArgumentException이 발생한다`() {
        // given
        val mineMap = MineMap()

        assertThrows<IllegalArgumentException> { // then
            mineMap.getCell(Position(1, 1)) // when
        }
    }



    @Test
    fun `방어적 복사를 통해 외부 수정으로 인한 내부 상태 볁경이 발생하지 않는지 확인`() {
        // given
        val mutableMap = mutableMapOf(
            Position(1, 1) to Cell(CellState.MINE),
            Position(2, 2) to Cell(CellState.EMPTY),
            Position(3, 3) to Cell(CellState.MINE)
        )
        val mineMap = MineMap(mutableMap)

        assertAll(
            { assertEquals("*", mineMap.getCell(Position(1, 1)).getStateSymbol()) },
            { assertEquals("C", mineMap.getCell(Position(2, 2)).getStateSymbol()) },
            { assertEquals("*", mineMap.getCell(Position(3, 3)).getStateSymbol()) },
            { assertEquals(3, mineMap.size) }
        )

        // when
        mutableMap.clear()

        // then
        assertAll(
            { assertEquals("*", mineMap.getCell(Position(1, 1)).getStateSymbol()) },
            { assertEquals("C", mineMap.getCell(Position(2, 2)).getStateSymbol()) },
            { assertEquals("*", mineMap.getCell(Position(3, 3)).getStateSymbol()) },
            { assertEquals(3, mineMap.size) }
        )
    }
}
