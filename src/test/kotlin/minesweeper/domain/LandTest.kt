package minesweeper.domain

import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.set.Mine
import minesweeper.domain.tile.state.set.NotChecked
import org.junit.jupiter.api.Test

class LandTest {
    @Test
    fun `Land - 문자열 타일 목록 반환 테스트`() {
        // given
        val tiles = Tiles(
            arrayListOf(
                Mine(Coordinate.of(0, 0)), NotChecked(Coordinate.of(0, 1), false),
                NotChecked(Coordinate.of(1, 0), false), Mine(Coordinate.of(1, 1))
            )
        )
        val land = Land.of(FIXED_CALIBRATED_POSITION, FIXED_CALIBRATED_POSITION, tiles)

        // when
        val actual = land.getTiles()

        // then
        val expected = listOf(
            listOf("*", "C"),
            listOf("C", "*")
        )

        assert(actual == expected)
    }

    companion object {
        private const val FIXED_POSITION = 2
        private const val CORRECTION_VALUE = 1
        private const val FIXED_CALIBRATED_POSITION = FIXED_POSITION - CORRECTION_VALUE
    }
}
