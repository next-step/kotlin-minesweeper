package minesweeper.domain

import io.kotest.assertions.assertSoftly
import minesweeper.domain.support.FixedPositionSelector
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineCountMapFactoryTest {
    private val positionGenerator = PositionGenerator(
        MineMapMeta(3, 3, 3),
        FixedPositionSelector
    )

    @Test
    fun `주변 지뢰 개수를 담고 있는 cell을 생성해 Map을 구성한다`() {
        // given
        val mineCountMapFactory = MineCountMapFactory(positionGenerator)

        // when
        val mineMap = mineCountMapFactory.create()

        // then
        assertSoftly {
            assertThat(mineMap.size).isEqualTo(9)
            assertThat(mineMap.getCell(Position(1, 1))).usingRecursiveComparison().isEqualTo(Mine())
            assertThat(mineMap.getCell(Position(1, 2))).usingRecursiveComparison().isEqualTo(Mine())
            assertThat(mineMap.getCell(Position(1, 3))).usingRecursiveComparison().isEqualTo(Mine())
            assertThat(mineMap.getCell(Position(2, 1))).usingRecursiveComparison().isEqualTo(Empty(2))
            assertThat(mineMap.getCell(Position(2, 2))).usingRecursiveComparison().isEqualTo(Empty(3))
            assertThat(mineMap.getCell(Position(2, 3))).usingRecursiveComparison().isEqualTo(Empty(2))
            assertThat(mineMap.getCell(Position(3, 1))).usingRecursiveComparison().isEqualTo(Empty(0))
            assertThat(mineMap.getCell(Position(3, 2))).usingRecursiveComparison().isEqualTo(Empty(0))
            assertThat(mineMap.getCell(Position(3, 3))).usingRecursiveComparison().isEqualTo(Empty(0))
        }
    }
}
