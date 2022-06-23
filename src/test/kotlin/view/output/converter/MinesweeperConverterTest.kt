package view.output.converter

import domain.Cell
import domain.Minesweeper
import domain.Row
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinesweeperConverterTest {
    @Test
    fun `MinesweeperConverter는 Minesweeper를 출력을 위한 문자열로 변환한다`() {
        val minesweeper = Minesweeper(
            Row(Cell.MINE, Cell.MINE, Cell.LAND, Cell.MINE, Cell.LAND),
            Row(Cell.MINE, Cell.LAND, Cell.MINE, Cell.MINE, Cell.LAND),
            Row(Cell.LAND, Cell.MINE, Cell.LAND, Cell.MINE, Cell.LAND),
            Row(Cell.MINE, Cell.MINE, Cell.LAND, Cell.LAND, Cell.LAND),
            Row(Cell.LAND, Cell.LAND, Cell.LAND, Cell.MINE, Cell.MINE)
        )

        val expected = """
            * * C * C
            * C * * C
            C * C * C
            * * C C C
            C C C * *
        """.trimIndent()

        assertThat(MinesweeperConverter.convert(minesweeper)).isEqualTo(expected)
    }
}
