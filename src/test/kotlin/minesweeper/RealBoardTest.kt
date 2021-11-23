package minesweeper

import minesweeper.domain.realBoard.RealBoard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class RealBoardTest {

    @Test
    fun `RealBoard 는 가로, 세로, 지뢰갯수를 받을 수 있다`() {
        // given
        val realBoard = RealBoard.of(10, 10, 1)

        // then
        assertAll({
            assertThat(realBoard.width).isEqualTo(10)
            assertThat(realBoard.height).isEqualTo(10)
            assertThat(realBoard.mines).isEqualTo(1)
        })
    }
}
