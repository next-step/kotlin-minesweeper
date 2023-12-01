package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PositionGeneratorTest {
    @Test
    fun `지뢰 메타 정보(지뢰 수, 지뢰맵 크기)가 주어질 때 mineCount 만큼의 지뢰 위치 정보 Set을 생성하고 반환한다`() {
        // given
        val mineMapMeta = MineMapMeta(10, 10, 10)
        val positionGenerator = PositionGenerator(mineMapMeta)

        // when
        val minePositions = positionGenerator.generateMinePositions()

        // then
        assertAll(
            { assertThat(minePositions.size).isEqualTo(mineMapMeta.mineCount) },
            { assertThat(minePositions.getValues().all { it.x in 1..10 && it.y in 1..10 }).isEqualTo(true) }
        )
    }

    @Test
    fun `지뢰 메타 정보와 지뢰 위치 Set이 주어질 때 빈칸의 위치 정보 Set을 반환한다`() {
        // given
        val mineMapMeta = MineMapMeta(10, 10, 10)
        val minePositions = setOf(
            Position(1, 1),
            Position(5, 5),
            Position(10, 10)
        ).toPositions()
        val positionGenerator = PositionGenerator(mineMapMeta)

        // when
        val emptyPositions = positionGenerator.generateEmptyPositions(minePositions)

        // then
        assertAll(
            { assertThat(emptyPositions.size).isEqualTo(mineMapMeta.getCellCount() - minePositions.size) },
            { assertThat(emptyPositions.getValues().all { it.x in 1..10 && it.y in 1..10 }).isEqualTo(true) }
        )
    }
}
