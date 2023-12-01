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
        mineMap.plantCell(Position(1, 1), Mine())

        // then
        assertEquals(1, mineMap.size)
    }

    @Test
    fun `위치 정보를 전달해 MineMap에 빈 상태를 심는다`() {
        // given
        val mineMap = MineMap()

        // when
        mineMap.plantCell(Position(1, 1), Empty())
        mineMap.plantCell(Position(2, 2), Empty())

        // then
        assertEquals(2, mineMap.size)
    }

    @Test
    fun `주어진 위치 정보에 놓인 Cell 객체를 가져온다`() {
        // given
        val mineMap = MineMap()
        val position = Position(1, 1)
        mineMap.plantCell(position, Empty())

        // when
        val cell = mineMap.getCell(position)

        // then
        assertEquals(true, cell is Empty)
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
            Position(1, 1) to Mine(),
            Position(2, 2) to Empty(),
            Position(3, 3) to Empty()
        )
        val mineMap = MineMap(mutableMap)
        assertEquals(3, mineMap.size)

        // when
        mutableMap.clear()

        // then
        assertEquals(3, mineMap.size)
    }
}
