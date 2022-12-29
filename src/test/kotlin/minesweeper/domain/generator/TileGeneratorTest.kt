package minesweeper.domain.generator

import minesweeper.domain.MineCount
import minesweeper.domain.tile.pos.Position
import minesweeper.domain.tile.strategy.MineFirstGenerateStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileGeneratorTest {
    @Test
    fun `TileGenerator - 지뢰 갯수 확인 테스트`() {
        // given
        val tileGenerator = TileGenerator(
            MineFirstGenerateStrategy(
                Position(FIXED_POSITION),
                Position(FIXED_POSITION),
                MineCount(FIXED_MINE_COUNT, FIXED_CALIBRATED_POSITION * FIXED_CALIBRATED_POSITION)
            )
        )

        // when
        val tiles = tileGenerator.generate()
        val mines = tiles.subList(0, FIXED_MINE_COUNT)

        // then
        assertThat(FIXED_MINE_COUNT).isEqualTo(tiles.count { it.isMine })
        assertThat(mines).allMatch { it.isMine }
    }

    companion object {
        private const val FIXED_POSITION = 2
        private const val FIXED_MINE_COUNT = 5
        private const val CORRECTION_VALUE = 1
        private const val FIXED_CALIBRATED_POSITION = FIXED_POSITION + CORRECTION_VALUE
    }
}
