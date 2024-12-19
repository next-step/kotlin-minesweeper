package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardLineTest {

    @Test
    fun `가지고 있는 셀의 크기보다 큰 값을 찾으려고 하면 에러를 던져야 한다`() {
        // given
        val boardLine = BoardLine(listOf(Cell.Mine, Cell.Mine, Cell.Mine))

        // when
        val result = assertThrows<IllegalArgumentException> { boardLine.find(3) }

        // then
        assertThat(result.message).isEqualTo("가지고 있는 셀의 크기를 초과해 찾을 수 없습니다")
    }

}