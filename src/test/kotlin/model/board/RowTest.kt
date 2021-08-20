package model.board

import model.board.Contents.MINE
import model.board.Contents.ZERO
import model.board.Contents.ONE
import model.board.State.COVERED
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

internal class RowTest {
    @Test
    fun `생성자 인자로 받은 cell list 가 변경되어도 Row 에 영향 주지 않는다`() {
        val cells = mutableListOf(
            Cell.get(MINE, COVERED)
        )

        val row = Row(cells)
        assertThat(row.width).isEqualTo(cells.size)

        cells.add(Cell.get(MINE, COVERED))
        assertThat(row.width).isNotEqualTo(cells.size)
    }

    @ParameterizedTest
    @EmptySource
    fun `빈 cells 로는 Row 생성 불가`(cells: List<Cell>) {
        assertThrows<IllegalArgumentException> {
            Row(cells)
        }
    }

    @ParameterizedTest
    @MethodSource("noMineCellUncoverProvider")
    fun `mine 갯수에 따라 uncover 되는 cell contents 달라짐`(mineCount: Int, contents: Contents) {
        val row = Row(Cell.DEFAULT_CELL, Cell.MINE_CELL, Cell.DEFAULT_CELL)
        row.uncover(0, mineCount)

        assertThat(row[0].contents).isEqualTo(contents)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 2])
    fun `mine cell 이면 갯수 상관없이 contents 는 mine`(mineCount: Int) {
        val row = Row(Cell.DEFAULT_CELL, Cell.MINE_CELL, Cell.DEFAULT_CELL)
        row.uncover(1, mineCount)

        assertThat(row[1].contents).isEqualTo(MINE)
    }

    companion object {
        @JvmStatic
        fun noMineCellUncoverProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        0,
                        ZERO
                    )
                },
                Arguments {
                    arrayOf(
                        1,
                        ONE
                    )
                }
            )
        }
    }
}
