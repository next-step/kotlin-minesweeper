package minesweeper.domain

import minesweeper.Mine
import minesweeper.NotMines
import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.Tiles
import minesweeper.domain.tile.pos.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LandTest {
    @Test
    fun `Land - 생성 확인 테스트(2x2, Mine NotChecked Mine NotChecked)`() {
        // given
        val tiles = Tiles(
            listOf(
                Mine(0, 0), NotMines(0, 1, Marking.TWO),
                NotMines(1, 0, Marking.TWO), Mine(1, 1)
            )
        )
        val land = Land.of(TWO - CORRECTION_VALUE, TWO - CORRECTION_VALUE, tiles)

        // when
        val actual = land.tiles

        // then
        val expected = listOf(Marking.MINE, Marking.TWO, Marking.TWO, Marking.MINE)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Land - 입력된 좌표 주변(3 x 3)의 지뢰 개수를 반환 테스트`() {
        // given
        val tiles = Tiles(
            listOf(
                Mine(0, 0), NotMines(0, 1, Marking.TWO), Mine(0, 2),
                NotMines(1, 0, Marking.TWO), NotMines(1, 1, Marking.FOUR), NotMines(1, 2, Marking.TWO),
                Mine(2, 0), NotMines(2, 1, Marking.TWO), Mine(2, 2)
            )
        )
        val land = Land.of(THREE - CORRECTION_VALUE, THREE - CORRECTION_VALUE, tiles)

        // when
        val actual = land.getMineCount(Coordinate.of(1, 1))

        // then
        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun `Land - 입력된 좌표 주변(2 x 2, 모서리)의 지뢰 개수를 반환 테스트`() {
        // given
        val tiles = Tiles(
            listOf(
                NotMines(0, 0, Marking.TWO), Mine(0, 1),
                Mine(1, 0), NotMines(1, 1, Marking.TWO)
            )
        )
        val land = Land.of(TWO - CORRECTION_VALUE, TWO - CORRECTION_VALUE, tiles)

        // when
        val actual = land.getMineCount(Coordinate.of(0, 0))

        // then
        assertThat(actual).isEqualTo(2)
    }

    companion object {
        private const val TWO = 2
        private const val THREE = 3
        private const val CORRECTION_VALUE = 1
    }
}
