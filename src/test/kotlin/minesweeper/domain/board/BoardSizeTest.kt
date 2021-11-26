package minesweeper.domain.board

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardSizeTest {
    @Test
    fun `board 너비는 0이면 안된다`() {
        // given
        val actual = runCatching { BoardSize(0, 1) }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("너비가 0입니다.")
    }

    @Test
    fun `board 높이는 0이면 안된다`() {
        // given
        val actual = runCatching { BoardSize(1, 0) }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("높이가 0입니다.")
    }
}
