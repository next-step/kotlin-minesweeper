package minesweeper

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CellsTest {

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
}
