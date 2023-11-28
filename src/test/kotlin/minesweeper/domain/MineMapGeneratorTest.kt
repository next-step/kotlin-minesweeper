package minesweeper.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MineMapGeneratorTest {
    @Test
    fun `지뢰 맵을 구성할 위치 정보가 주어졌을 때 MineMap을 생성할 수 있다`() {
        // given
        val mineMapMeta = MineMapMeta(3, 3, 3)
        val minePositions = setOf(
            Position(1, 1),
            Position(2, 2),
            Position(3, 3)
        ).toPositions()
        val emptyPositions = setOf(
            Position(1, 2),
            Position(1, 3),
            Position(2, 1),
            Position(2, 3),
            Position(3, 1),
            Position(3, 2)
        ).toPositions()

        // when
        val mineMap = MineMapGenerator.generate(minePositions, emptyPositions)

        // then
        assertEquals(mineMapMeta.getCellCount(), mineMap.values.size)
    }
}
