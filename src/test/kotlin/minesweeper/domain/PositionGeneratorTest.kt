package minesweeper.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PositionGeneratorTest {
    @Test
    fun `지뢰 메타 정보(지뢰 수, 지뢰맵 크기)가 주어질 때 mineCount 만큼의 지뢰 위치 정보 Set을 생성하고 반환한다`() {
        // given
        val mineMapMeta = MineMapMeta(10, 10, 10)
        val positionGenerator = PositionGenerator(mineMapMeta)

        // when
        val minePositions = positionGenerator.generateMinePositions()

        // then
        assertEquals(mineMapMeta.mineCount, minePositions.size)
        assertEquals(true, minePositions.all { it.x in 1..10 && it.y in 1..10 })
    }

    @Test
    fun `지뢰 메타 정보와 지뢰 위치 Set이 주어질 때 빈칸의 위치 정보 Set을 반환한다`() {
        // given
        val mineMapMeta = MineMapMeta(10, 10, 10)
        val minePositions = setOf(
            Position(1, 1),
            Position(5, 5),
            Position(10, 10)
        )
        val positionGenerator = PositionGenerator(mineMapMeta)

        // when
        val emptyPositions = positionGenerator.generateEmptyPositions(minePositions)

        // then
        assertEquals(mineMapMeta.getCellCount() - minePositions.size, emptyPositions.size)
        assertEquals(true, emptyPositions.all { it.x in 1..10 && it.y in 1..10 })
    }
}
