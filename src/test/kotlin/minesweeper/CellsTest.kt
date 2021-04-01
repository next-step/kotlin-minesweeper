package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import java.util.stream.Stream

class CellsTest {
    private val mines = listOf(
        false, false, false, false, false, true, true, false, false, true, true, false,
        false, false, true, true, false, true, false, false, true, true, false, false, true
    ).map { Cell(it) }
    private val board = Cells(mines, 5, 5)

    @Test
    fun `cells를 정상적으로 생성한다`() {
        val width = 10
        val height = 10
        val cellsDump = (1..width * height).map { Cell(false) }

        Cells(cellsDump, width, height)
    }

    @Test
    fun `셀의 갯수가 width * height가 아닐 경우 예외가 발생한다`() {
        val width = 10
        val height = 10
        val cellsDump = (1..width * 5).map { Cell(false) }

        assertThrows<IllegalArgumentException> {
            Cells(cellsDump, width, height)
        }
    }

    @Test
    fun `너비나 높이가 0보다 크지 않으면 예외가 발생한다`() {
        val width = 10
        val height = -1

        assertThrows<IllegalArgumentException> {
            Cells(listOf(), width, height)
        }
    }

    @ParameterizedTest
    @MethodSource("cellValueResultData")
    fun `셀의 숫자를 정상적으로 가져온다`(index: Int, cellValue: Int) {
        assertThat(board.getCellValue(index)).isEqualTo(cellValue)
    }

    companion object {
        @JvmStatic
        private fun cellValueResultData(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(0, 2),
                Arguments.of(1, 2),
                Arguments.of(2, 1),
                Arguments.of(3, 1),
                Arguments.of(4, 1),
                Arguments.of(7, 1),
                Arguments.of(8, 2),
                Arguments.of(11, 5),
                Arguments.of(12, 2),
                Arguments.of(13, 3),
                Arguments.of(16, 5),
                Arguments.of(18, 3),
                Arguments.of(19, 2),
                Arguments.of(22, 2),
                Arguments.of(23, 2)
            )
        }
    }
}
