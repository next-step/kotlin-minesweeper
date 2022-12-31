package minesweeper.domain.tile.state.set

import minesweeper.NotChecked
import minesweeper.domain.tile.Marking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class NotCheckedTest {
    @Test
    fun `NotChecked - 심볼 확인(CLOSED) 테스트`() {
        // given
        val given = NotChecked(0, 0, false)

        // when
        val actual = given.marking

        // then
        Assertions.assertThat(actual).isEqualTo(Marking.CLOSED)
    }
}
