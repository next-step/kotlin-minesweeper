package model.board

import model.board.Contents.MINE
import model.board.State.COVERED
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

internal class BoardTest {
    @Test
    fun `생성자 인자로 받은 row list 가 변경되어도 Board 에 영향 주지 않는다`() {
        val rows = mutableListOf(
            Row(Cell.get(MINE, COVERED))
        )

        val board = Board(rows)
        assertThat(board.height).isEqualTo(rows.size)

        rows.add(Row(Cell.get(MINE, COVERED)))
        assertThat(board.height).isNotEqualTo(rows.size)
    }

    @Test
    fun `입력받은 row 의 길이가 다르면 예외 발생`() {
        val rows = listOf(
            Row(Cell.get(MINE, COVERED)),
            Row(Cell.get(MINE, COVERED), Cell.get(MINE, COVERED))
        )

        assertThrows<IllegalArgumentException> {
            Board(rows)
        }
    }

    @ParameterizedTest
    @EmptySource
    fun `빈 rows 로는 Board 생성 불가`(rows: List<Row>) {
        assertThrows<IllegalArgumentException> {
            Board(rows)
        }
    }
}
