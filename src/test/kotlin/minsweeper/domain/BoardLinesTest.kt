package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardLinesTest {
    @Test
    fun `가지고 있는 라인의 크기보다 큰 값을 찾으려고 하면 에러를 던져야 한다`() {
        // given
        val boardLines = BoardLines(
            listOf(
                BoardLine(listOf(Cell.Mine, Cell.Mine, Cell.Mine)),
                BoardLine(listOf(Cell.Mine, Cell.Mine, Cell.Mine)),
                BoardLine(listOf(Cell.Mine, Cell.Mine, Cell.Mine)),
            )
        )

        // when
        val result = assertThrows<IllegalArgumentException> { boardLines.find(3) }

        // then
        assertThat(result.message).isEqualTo("가지고 있는 라인의 크기를 초과해 찾을 수 없습니다")
    }
}