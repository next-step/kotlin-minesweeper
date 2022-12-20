package minesweeper.domain.tile

import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.state.set.Mine
import minesweeper.domain.tile.state.set.NotChecked
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class TilesTest {
    @Test
    fun `Tiles - 중복 타일 생성에 대한 예외처리 테스트`() {
        // given
        val coordinate = Coordinate.of(0, 0)

        // when, then
        assertThatThrownBy { Tiles(listOf(NotChecked(coordinate, false), NotChecked(coordinate, false))) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("타일은 중복될 수 없습니다.")
    }

    @Test
    fun `Tiles - 타일이 없는 경우에 대한 예외처리 테스트`() {
        // given, when, then
        assertThatThrownBy { Tiles(emptyList()) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("타일은 적어도 1개 이상이어야 합니다.")
    }

    @Test
    fun `Tiles - 문자열 타일 목록 반환 테스트`() {
        // given
        val tile1 = NotChecked(Coordinate.of(0, 0), false)
        val tile2 = Mine(Coordinate.of(0, 1))
        val tile3 = NotChecked(Coordinate.of(0, 2), false)
        val tiles = Tiles(listOf(tile1, tile2, tile3))

        // when
        val actual = tiles.getListAsString()

        // then
        assertThat(actual.count { it == Marking.MINE.symbol }).isEqualTo(1)
        assertThat(actual.count { it == Marking.CLOSED.symbol }).isEqualTo(2)
    }
}
