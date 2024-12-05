package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BoardTest {

    @Test
    fun `지뢰찾기 보드 생성이 정상적으로 된다`() {
        assertDoesNotThrow {
            Board(
                rows = 3,
                columns = 3,
                mineCount = 2,
            )
        }
    }

    @CsvSource(
        "0, 0, 0",
        "0, 0, -1",
        "0, -1, 0",
        "-1, 0, 0",
        "-1, -1, -1",
    )
    @ParameterizedTest
    fun `지뢰찾기 보드에 0 이하의 값이 들어오면 예외가 발생한다`(rows: Int, columns: Int, mineCount: Int) {
        assertThrows<IllegalArgumentException> {
            Board(
                rows = rows,
                columns = columns,
                mineCount = mineCount,
            )
        }
    }

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `지뢰찾기 보드를 생성하면 지뢰가 심어진다`(mineCount: Int) {
        val board = Board(
            rows = 5,
            columns = 5,
            mineCount = mineCount,
        )

        val expected = board.board.sumOf { row -> row.count {it.isMine } }
        mineCount shouldBe expected
    }
}
