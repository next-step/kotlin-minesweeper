package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.Symbol
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.pos.Position
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class NotCheckedTest {
    @Test
    fun `NotChecked - 심볼 확인 테스트`() {
        // given
        val given = NotChecked(Coordinate(Position(0), Position(0)), false)

        // when
        val actual = given.symbol

        // then
        Assertions.assertThat(actual).isEqualTo(Symbol.CLOSED)
    }
}
