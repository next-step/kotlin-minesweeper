package minesweeper.domain

import io.kotest.assertions.assertSoftly
import minesweeper.domain.support.FixedPositionSelector
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionGeneratorTest {
    private val mineMapMeta = MineMapMeta(5, 5, 5)
    private val positionGenerator = PositionGenerator(
        mineMapMeta = mineMapMeta,
        positionSelector = FixedPositionSelector
    )

    @Test
    fun `지뢰 위치와 지뢰가 아닌 위치를 생성한다`() {
        // given, when
        val minePositions = positionGenerator.generateMinePositions()
        val emptyPositions = positionGenerator.generateEmptyPositions(minePositions)

        assertSoftly { // then
            assertThat(minePositions)
                .containsExactly(
                    Position(1, 1), Position(1, 2), Position(1, 3), Position(1, 4), Position(1, 5)
                )
            assertThat(emptyPositions)
                .containsExactly(
                    Position(2, 1), Position(2, 2), Position(2, 3), Position(2, 4), Position(2, 5),
                    Position(3, 1), Position(3, 2), Position(3, 3), Position(3, 4), Position(3, 5),
                    Position(4, 1), Position(4, 2), Position(4, 3), Position(4, 4), Position(4, 5),
                    Position(5, 1), Position(5, 2), Position(5, 3), Position(5, 4), Position(5, 5)
                )
        }
    }
}
