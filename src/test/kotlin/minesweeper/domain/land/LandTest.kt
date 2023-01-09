package minesweeper.domain.land

import minesweeper.Land
import minesweeper.Mine
import minesweeper.NotChecked
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
            Mine(0, 0), NotMines(1, 0, Marking.TWO),
            NotMines(0, 1, Marking.TWO), Mine(1, 1)
        )
        val land = Land(2, 2, tiles)

        // when
        val actual = land.tiles

        // then
        assertThat(actual).isEqualTo(
            listOf(
                Marking.MINE, Marking.TWO,
                Marking.TWO, Marking.MINE
            )
        )
    }

    @Test
    fun `Land - 입력된 좌표 주변(3 x 3)의 지뢰 개수를 반환 테스트`() {
        // given
        val tiles = Tiles(
            Mine(0, 0), NotMines(1, 0, Marking.TWO), Mine(2, 0),
            NotMines(0, 1, Marking.TWO), NotMines(1, 1, Marking.FOUR), NotMines(2, 1, Marking.TWO),
            Mine(0, 2), NotMines(1, 2, Marking.TWO), Mine(2, 2)
        )
        val land = Land(3, 3, tiles)

        // when
        val actual = land.getMineCount(Coordinate.of(1, 1))

        // then
        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun `Land - 연쇄 확인 테스트, 지뢰가 가장 끝에 있는 경우`() {
        // given
        val tiles = Tiles(
            NotChecked(0, 0, false), NotChecked(1, 0, false), NotChecked(2, 0, false), NotChecked(3, 0, false), NotChecked(4, 0, false),
            NotChecked(0, 1, false), NotChecked(1, 1, false), NotChecked(2, 1, false), NotChecked(3, 1, false), NotChecked(4, 1, false),
            NotChecked(0, 2, false), NotChecked(1, 2, false), NotChecked(2, 2, false), NotChecked(3, 2, false), NotChecked(4, 2, false),
            NotChecked(0, 3, false), NotChecked(1, 3, false), NotChecked(2, 3, false), NotChecked(3, 3, false), NotChecked(4, 3, false),
            NotChecked(0, 4, false), NotChecked(1, 4, false), NotChecked(2, 4, false), NotChecked(3, 4, false), NotChecked(4, 4, true)
        )
        val land = Land(5, 5, tiles)

        // when
        land.selectTile(Coordinate.of(0, 0))
        val actual = land.tiles

        // then
        assertThat(actual).isEqualTo(
            listOf(
                Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.EMPTY,
                Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.EMPTY,
                Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.EMPTY,
                Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.ONE, Marking.ONE,
                Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.ONE, Marking.CLOSED,
            )
        )
    }

    @Test
    fun `Land - 연쇄 확인 테스트, 지뢰가 중간 경계에 있는 경우`() {
        // given
        val tiles = Tiles(
            NotChecked(0, 0, false), NotChecked(1, 0, false), NotChecked(2, 0, false), NotChecked(3, 0, false), NotChecked(4, 0, false),
            NotChecked(0, 1, false), NotChecked(1, 1, false), NotChecked(2, 1, false), NotChecked(3, 1, false), NotChecked(4, 1, false),
            NotChecked(0, 2, true), NotChecked(1, 2, false), NotChecked(2, 2, true), NotChecked(3, 2, false), NotChecked(4, 2, true),
            NotChecked(0, 3, false), NotChecked(1, 3, false), NotChecked(2, 3, false), NotChecked(3, 3, false), NotChecked(4, 3, false),
            NotChecked(0, 4, false), NotChecked(1, 4, false), NotChecked(2, 4, false), NotChecked(3, 4, false), NotChecked(4, 4, false)
        )
        val land = Land(5, 5, tiles)

        // when
        land.selectTile(Coordinate.of(0, 0))
        val actual = land.tiles

        // then
        assertThat(actual).isEqualTo(
            listOf(
                Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.EMPTY, Marking.EMPTY,
                Marking.ONE, Marking.TWO, Marking.ONE, Marking.TWO, Marking.ONE,
                Marking.CLOSED, Marking.CLOSED, Marking.CLOSED, Marking.CLOSED, Marking.CLOSED,
                Marking.CLOSED, Marking.CLOSED, Marking.CLOSED, Marking.CLOSED, Marking.CLOSED,
                Marking.CLOSED, Marking.CLOSED, Marking.CLOSED, Marking.CLOSED, Marking.CLOSED,
            )
        )
    }

    @Test
    fun `Land - 지뢰 폭발`() {
        // given
        val tiles = Tiles(
            Mine(0, 0), Mine(1, 0),
            Mine(0, 1), Mine(1, 1)
        )
        val land = Land(2, 2, tiles)

        // when
        val actual = land.selectTile(Coordinate.of(0, 0))

        // then
        assertThat(actual).isFalse
    }

    @Test
    fun `Land - 모든 타일이 체크되었는지 확인`() {
        // given
        val tiles = Tiles(
            NotChecked(0, 0, false), NotChecked(1, 0, false),
            NotChecked(0, 1, false), NotChecked(1, 1, true)
        )
        val land = Land(2, 2, tiles)

        // when, then
        land.selectTile(Coordinate.of(0, 0))
        land.selectTile(Coordinate.of(1, 0))
        assertThat(land.isAllOpened).isFalse

        land.selectTile(Coordinate.of(0, 1))
        assertThat(land.isAllOpened).isTrue
    }
}
