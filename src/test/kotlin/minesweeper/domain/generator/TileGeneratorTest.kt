package minesweeper.domain.generator

import minesweeper.domain.MineCount
import minesweeper.domain.land.state.Area
import minesweeper.domain.tile.strategy.MineFirstGenerateStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileGeneratorTest {
    @Test
    fun `TileGenerator - 지뢰 갯수 확인 테스트`() {
        // given
        val tileGenerator = TileGenerator(
            MineFirstGenerateStrategy(
                Area(FIXED_SIZE, FIXED_SIZE),
                MineCount(FIXED_MINE_COUNT, FIXED_SIZE * FIXED_SIZE)
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
        private const val FIXED_SIZE = 3
        private const val FIXED_MINE_COUNT = 5
    }
}
