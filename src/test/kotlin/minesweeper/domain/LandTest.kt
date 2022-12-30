package minesweeper.domain

import minesweeper.Mine
import minesweeper.NotChecked
import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.Tiles
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LandTest {
    @Test
    fun `Land - 생성 확인 테스트(2x2, Mine NotChecked Mine NotChecked)`() {
        // given
        val tiles = Tiles(
            listOf(
                Mine(0, 0), NotChecked(0, 1, false),
                NotChecked(1, 0, false), Mine(1, 1)
            )
        )
        val land = Land.of(FIXED_CALIBRATED_POSITION, tiles)

        // when
        val actual = land.getTiles()

        // then
        val expected = listOf(Marking.MINE, Marking.CLOSED, Marking.CLOSED, Marking.MINE)

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        private const val FIXED_POSITION = 2
        private const val CORRECTION_VALUE = 1
        private const val FIXED_CALIBRATED_POSITION = FIXED_POSITION - CORRECTION_VALUE
    }
}
