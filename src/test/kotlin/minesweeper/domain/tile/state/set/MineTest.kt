package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.Symbol
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.pos.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineTest {
    @Test
    fun `Mine - 심볼 확인 테스트`() {
        // given
        val given = Mine(Coordinate(Position(0), Position(0)))

        // when
        val actual = given.symbol

        // then
        assertThat(actual).isEqualTo(Symbol.MINE)
    }
}
