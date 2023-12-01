package minesweeper.domain

import io.kotest.assertions.assertSoftly
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineCountMapFactoryTest {

    @Test
    fun `주변 지뢰 개수를 담고 있는 cell을 생성해 Map을 구성한다`() {
        // given
        val emptyPositions = Positions(
            setOf(
                Position(1, 1),
                Position(2, 2),
                Position(3, 3)
            )
        )
        val minePositions = Positions(
            setOf(
                Position(1, 2),
                Position(1, 3),
                Position(2, 1),
                Position(2, 3),
                Position(3, 1),
                Position(3, 2)
            )
        )

        // when
        val mineMap = MineCountMapFactory.create(minePositions, emptyPositions)

        // then
        assertSoftly {
            assertThat(mineMap.size).isEqualTo(9)
            assertThat(mineMap.getCell(Position(1, 1))).usingRecursiveComparison().isEqualTo(Empty(2))
            assertThat(mineMap.getCell(Position(2, 2))).usingRecursiveComparison().isEqualTo(Empty(6))
            assertThat(mineMap.getCell(Position(3, 3))).usingRecursiveComparison().isEqualTo(Empty(2))
            assertThat(mineMap.getCell(Position(1, 2))).usingRecursiveComparison().isEqualTo(Mine)
            assertThat(mineMap.getCell(Position(1, 3))).usingRecursiveComparison().isEqualTo(Mine)
            assertThat(mineMap.getCell(Position(2, 1))).usingRecursiveComparison().isEqualTo(Mine)
            assertThat(mineMap.getCell(Position(2, 3))).usingRecursiveComparison().isEqualTo(Mine)
            assertThat(mineMap.getCell(Position(3, 1))).usingRecursiveComparison().isEqualTo(Mine)
            assertThat(mineMap.getCell(Position(3, 2))).usingRecursiveComparison().isEqualTo(Mine)
        }
    }
}
