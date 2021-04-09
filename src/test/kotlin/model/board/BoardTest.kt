package model.board

import model.board.Contents.MINE
import model.board.State.COVERED
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource

internal class BoardTest {
    @ParameterizedTest
    @MethodSource("rowsProvider")
    fun `생성자 인자로 받은 row list 가 변경되어도 Board 에 영향 주지 않는다`(rows: MutableList<Row>) {
        val board = Board(rows)
        assertThat(board.height).isEqualTo(rows.size)

        rows.add(Row(cell))
        assertThat(board.height).isNotEqualTo(rows.size)
    }

    @ParameterizedTest
    @MethodSource("differentSizeRowsProvider")
    fun `입력받은 row 의 길이가 다르면 예외 발생`(rows: List<Row>) {
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

    companion object {
        private val cell = Cell.get(MINE, COVERED)

        @JvmStatic
        private fun differentSizeRowsProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        listOf(
                            Row(cell),
                            Row(cell, cell)
                        )
                    )
                }
            )
        }

        @JvmStatic
        private fun rowsProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        mutableListOf(
                            Row(cell)
                        )
                    )
                }
            )
        }
    }
}
