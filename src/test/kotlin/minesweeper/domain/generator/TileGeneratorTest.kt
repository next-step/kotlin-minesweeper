package minesweeper.domain.generator

import minesweeper.domain.MineCount
import minesweeper.domain.tile.pos.Position
import minesweeper.domain.tile.strategy.RandomGenerateStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileGeneratorTest {
    @Test
    fun `TileGenerator - 타일 생성 테스트`() {
        // given
        val tileGenerator = TileGenerator(
            RandomGenerateStrategy(
                Position(FIXED_POSITION),
                Position(FIXED_POSITION),
                MineCount(FIXED_MINE_COUNT, FIXED_CALIBRATED_POSITION * FIXED_CALIBRATED_POSITION)
            )
        )

        // when
        val tiles = tileGenerator.generate()

        // then
        assertThat(FIXED_MINE_COUNT).isEqualTo(tiles.count { it.isMine })
    }

    companion object {
        private const val FIXED_POSITION = 2
        private const val FIXED_MINE_COUNT = 5
        private const val CORRECTION_VALUE = 1
        private const val FIXED_CALIBRATED_POSITION = FIXED_POSITION + CORRECTION_VALUE
    }
}
