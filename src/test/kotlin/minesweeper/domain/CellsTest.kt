package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
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

        val cells = Cells(cellsDump, width, height)

        assertThat(cells.width).isEqualTo(width)
        assertThat(cells.height).isEqualTo(height)
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
        board.enterCell(Location.of("0, 1"))
        assertThat(board.isAllOpen()).isFalse()
        board.enterCell(Location.of("0, 3"))
        assertThat(board.isAllOpen()).isFalse()
        board.enterCell(Location.of("2, 2"))
        assertThat(board.isAllOpen()).isFalse()
        board.enterCell(Location.of("4, 3"))
        assertThat(board.isAllOpen()).isTrue()
    }

    @Test
    fun `지뢰가 없는 셀을 선택하면 주변의 지뢰가 없는 셀도 열린다`() {
        assertThat(board.enterCell(Location.of("1, 3"))).isTrue()

        assertAll(
            { assertThat(board.cells[2].isOpen).isTrue() },
            { assertThat(board.cells[3].isOpen).isTrue() },
            { assertThat(board.cells[4].isOpen).isTrue() },
            { assertThat(board.cells[7].isOpen).isTrue() },
            { assertThat(board.cells[8].isOpen).isTrue() },
            { assertThat(board.cells[9].isOpen).isFalse() },
            { assertThat(board.cells[12].isOpen).isTrue() },
            { assertThat(board.cells[13].isOpen).isTrue() },
            { assertThat(board.cells[14].isOpen).isFalse() }
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["1,0", "1,1", "1,4", "1,5", "2,4", "3,0", "3,2", "4,0", "4,1", "4,4"])
    fun `지뢰가 있는 셀을 선택하면 false를 리턴한다`(location: String) {
        assertThat(board.enterCell(Location.of(location))).isFalse()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "0,0", "0,1", "0,2", "0,3", "0,4", "1,2", "1,3", "2,1",
            "2,2", "2,3", "3,1", "3,3", "3,4", "4,2", "4,3"
        ]
    )
    fun `지뢰가 없는 셀을 선택하면 true를 리턴한다`(location: String) {
        assertThat(board.enterCell(Location.of(location))).isTrue()
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
