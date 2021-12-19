package mine_tdd

import mine_tdd.cell.Cells
import mine_tdd.cell.MineCell
import mine_tdd.cell.NoneCell
import mine_tdd.cell.Position
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CellsTest {
    @Test
    fun `Position(1,1)인 셀의 주변 지뢰 개수를 구한다`() {
        val position = Position(1, 1)
        val cells = Cells(listOf(
            MineCell(Position(0, 0)),
            NoneCell(Position(0, 1)),
            NoneCell(Position(0, 2)),
            NoneCell(Position(1, 0)),
            NoneCell(Position(1, 1)),
            NoneCell(Position(1, 2)),
            NoneCell(Position(2, 0)),
            NoneCell(Position(2, 1)),
            MineCell(Position(2, 2)),
        ))

        val count = cells.findMineCount(position)

        Assertions.assertThat(count).isEqualTo(2)
    }
}
