package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
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

    @Test
    fun `모든 셀이 열리면 true를 반환한다`() {
        assertThat(board.isAllOpen()).isFalse()
        board.enterCell(1)
        assertThat(board.isAllOpen()).isFalse()
        board.enterCell(3)
        assertThat(board.isAllOpen()).isFalse()
        board.enterCell(12)
        assertThat(board.isAllOpen()).isFalse()
        board.enterCell(23)
        assertThat(board.isAllOpen()).isTrue()
    }

    @Test
    fun `지뢰가 없는 셀을 선택하면 주변의 지뢰가 없는 셀도 열린다`() {
        assertThat(board.enterCell(8)).isTrue()
        assertThat(board.cells[2].isOpen).isTrue()
        assertThat(board.cells[3].isOpen).isTrue()
        assertThat(board.cells[4].isOpen).isTrue()
        assertThat(board.cells[7].isOpen).isTrue()
        assertThat(board.cells[8].isOpen).isTrue()
        assertThat(board.cells[9].isOpen).isFalse()
        assertThat(board.cells[12].isOpen).isTrue()
        assertThat(board.cells[13].isOpen).isTrue()
        assertThat(board.cells[14].isOpen).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 6, 9, 10, 14, 15, 17, 20, 21, 24])
    fun `지뢰가 있는 셀을 선택하면 false를 리턴한다`(index: Int) {
        assertThat(board.enterCell(index)).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 7, 8, 11, 12, 13, 16, 18, 19, 22, 23])
    fun `지뢰가 없는 셀을 선택하면 true를 리턴한다`(index: Int) {
        assertThat(board.enterCell(index)).isTrue()
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
