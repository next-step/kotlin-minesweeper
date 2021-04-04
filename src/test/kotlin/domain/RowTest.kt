package domain

import domain.Contents.MINE
import domain.State.COVERED
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource

internal class RowTest {
    @ParameterizedTest
    @MethodSource("cellsProvider")
    fun `생성자 인자로 받은 cell list 가 변경되어도 Row 에 영향 주지 않는다`(cells: MutableList<Cell>) {
        val row = Row(cells)
        Assertions.assertThat(row.width).isEqualTo(cells.size)

        cells.add(Cell(MINE, COVERED))
        Assertions.assertThat(row.width).isNotEqualTo(cells.size)
    }

    @ParameterizedTest
    @EmptySource
    fun `빈 cells 로는 Row 생성 불가`(cells: List<Cell>) {
        assertThrows<IllegalArgumentException> {
            Row(cells)
        }
    }

    companion object {
        @JvmStatic
        private fun cellsProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        mutableListOf(
                            Cell(MINE, COVERED)
                        )
                    )
                }
            )
        }
    }
}