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
                height = 3,
                width = 3,
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
                height = rows,
                width = columns,
                mineCount = mineCount,
            )
        }
    }

    @Test
    fun `지뢰찾기 보드에 지뢰 개수가 전체 칸의 개수보다 크면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Board(
                height = 3,
                width = 3,
                mineCount = 10,
            )
        }
    }

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `지뢰찾기 보드를 생성하면 지뢰가 심어진다`(mineCount: Int) {
        val board = Board(
            height = 5,
            width = 5,
            mineCount = mineCount,
        )

        val expected = board.countMines()
        mineCount shouldBe expected
    }
}
