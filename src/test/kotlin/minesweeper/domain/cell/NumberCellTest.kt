package minesweeper.domain.cell

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NumberCellTest {

    private val DEFAULT_POSITION = Pair(1, 1).toPosition()

    @DisplayName(value = "주변의 폭탄의 수는 0보다 커야 한다. ")
    @Test
    fun checkAroundMineCountSmallTest() {
        Assertions.assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy {
                NumberCell(-1, DEFAULT_POSITION)
            }
    }

    @DisplayName(value = "주변의 폭탄의 수는 8보다 작아야 한다. ")
    @Test
    fun checkAroundMineCountOverTest() {
        Assertions.assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy {
                NumberCell(9, DEFAULT_POSITION)
            }
    }
}
