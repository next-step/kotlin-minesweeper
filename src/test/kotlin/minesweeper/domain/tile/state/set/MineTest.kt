package minesweeper.domain.tile.state.set

import minesweeper.domain.tile.Marking
import minesweeper.domain.tile.pos.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineTest {
    @Test
    fun `Mine - 심볼 확인(MINE) 테스트`() {
        // given
        val given = Mine(Coordinate.of(0, 0))

        // when
        val actual = given.marking

        // then
        assertThat(actual).isEqualTo(Marking.MINE)
    }
}
