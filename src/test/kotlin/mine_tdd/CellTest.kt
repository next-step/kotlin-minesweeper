package mine_tdd

import mine_tdd.cell.Cells
import mine_tdd.cell.MineCell
import mine_tdd.cell.NoneCell
import mine_tdd.cell.Position
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `Position(1,1)인 셀의 주변 위치 리스트를 가져온다`() {
        val cell = NoneCell(Position(1, 1))
        val expectList = listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        )

        val nearPositions = cell.findNearPosition()

        Assertions.assertThat(nearPositions.size).isEqualTo(8)
        Assertions.assertThat(nearPositions).isEqualTo(expectList)
    }

    @Test
    fun `Position(1,1)인 셀의 주변 지뢰 개수를 얻을 수 있다`() {
        val position = Position(1, 1)
        val cells = Cells(listOf(
            NoneCell(Position(0, 0), 0),
            NoneCell(Position(0, 1), 0),
            NoneCell(Position(0, 2), 0),
            NoneCell(Position(1, 0), 0),
            NoneCell(Position(1, 1), 1),
            NoneCell(Position(1, 2), 1),
            NoneCell(Position(2, 0), 0),
            NoneCell(Position(2, 1), 1),
            MineCell(Position(2, 2)),
        ))

        val cell = cells.findCell(position)
        val count = cell?.nearMineCount

        Assertions.assertThat(count).isEqualTo(1)
    }
}
