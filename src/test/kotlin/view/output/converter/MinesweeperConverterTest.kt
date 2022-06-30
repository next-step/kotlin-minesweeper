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
            Row(Cell.Mine, Cell.Mine, Cell.Land.FOUR, Cell.Mine, Cell.Land.TWO),
            Row(Cell.Mine, Cell.Land.FIVE, Cell.Mine, Cell.Mine, Cell.Land.THREE),
            Row(Cell.Land.FOUR, Cell.Mine, Cell.Land.FIVE, Cell.Mine, Cell.Land.TWO),
            Row(Cell.Mine, Cell.Mine, Cell.Land.FOUR, Cell.Land.THREE, Cell.Land.THREE),
            Row(Cell.Land.TWO, Cell.Land.TWO, Cell.Land.TWO, Cell.Mine, Cell.Mine)
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
