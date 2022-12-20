package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.pos.Coordinate
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class NotCheckedTest {
    @Test
    fun `NotChecked - 심볼 확인 테스트`() {
        // given
        val given = NotChecked(Coordinate.of(0, 0), false)

        // when
        val actual = given.marking

        // then
        Assertions.assertThat(actual).isEqualTo(Marking.CLOSED)
    }
}
