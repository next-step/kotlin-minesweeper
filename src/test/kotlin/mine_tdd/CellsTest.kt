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
        val cells = Cells(
            listOf(
                MineCell(Position(0, 0)),
                NoneCell(Position(0, 1)),
                NoneCell(Position(0, 2)),
                NoneCell(Position(1, 0)),
                NoneCell(Position(1, 1)),
                NoneCell(Position(1, 2)),
                NoneCell(Position(2, 0)),
                NoneCell(Position(2, 1)),
                MineCell(Position(2, 2)),
            )
        )

        val count = cells.findMineCount(position)

        Assertions.assertThat(count).isEqualTo(2)
    }

    @Test
    fun `일반 셀을 open 할 수 있다`() {
        val position = Position(1, 1)
        val cells = Cells(
            listOf(
                MineCell(Position(0, 0)),
                NoneCell(Position(0, 1)),
                NoneCell(Position(0, 2)),
                NoneCell(Position(1, 0)),
                NoneCell(Position(1, 1)),
                NoneCell(Position(1, 2)),
                NoneCell(Position(2, 0)),
                NoneCell(Position(2, 1)),
                MineCell(Position(2, 2)),
            )
        )

        cells.open(cells.findCell(position))

        Assertions.assertThat(cells.findCell(position)?.isOpen).isEqualTo(true)
    }

    @Test
    fun `지뢰 셀을 open 할 수 없다`() {
        val position = Position(2, 2)
        val cells = Cells(
            listOf(
                MineCell(Position(0, 0)),
                NoneCell(Position(0, 1)),
                NoneCell(Position(0, 2)),
                NoneCell(Position(1, 0)),
                NoneCell(Position(1, 1)),
                NoneCell(Position(1, 2)),
                NoneCell(Position(2, 0)),
                NoneCell(Position(2, 1)),
                MineCell(Position(2, 2)),
            )
        )

        cells.open(cells.findCell(position))

        Assertions.assertThat(cells.findCell(position)?.isOpen).isEqualTo(false)
    }

    @Test
    fun `일반 셀을 클릭하면 셀의 주변에서 지뢰가 없는 인접한 칸 리스트를 얻을 수 있다`() {
        val position = Position(0, 0)
        val cells = Cells(
            listOf(
                NoneCell(Position(0, 0), 0),
                NoneCell(Position(0, 1), 0),
                NoneCell(Position(0, 2), 0),
                NoneCell(Position(1, 0), 0),
                NoneCell(Position(1, 1), 1),
                NoneCell(Position(1, 2), 1),
                NoneCell(Position(2, 0), 0),
                NoneCell(Position(2, 1), 1),
                MineCell(Position(2, 2)),
            )
        )
        val expected = listOf(
            cells.findCell(Position(0, 1)),
            cells.findCell(Position(1, 0))
        )

        val list = cells.findZeroMineCells(cells.findCell(position)!!)

        Assertions.assertThat(list).isEqualTo(expected)
    }

    @Test
    fun `일반 셀을 클릭하면 지뢰가 없는 인접한 칸 리스트를 얻을 수 있다`() {
        val position = Position(0, 0)
        val cells = Cells(
            listOf(
                NoneCell(Position(0, 0), 0),
                NoneCell(Position(0, 1), 0),
                NoneCell(Position(0, 2), 0),
                NoneCell(Position(1, 0), 0),
                NoneCell(Position(1, 1), 1),
                NoneCell(Position(1, 2), 1),
                NoneCell(Position(2, 0), 0),
                NoneCell(Position(2, 1), 1),
                MineCell(Position(2, 2)),
            )
        )
        val expected = listOf(
            cells.findCell(Position(0, 0)),
            cells.findCell(Position(0, 1)),
            cells.findCell(Position(1, 0)),
            cells.findCell(Position(0, 2)),
            cells.findCell(Position(2, 0)),
        )

        val list = cells.getZeroMineCellList(position)

        Assertions.assertThat(list).isEqualTo(expected)
    }

    @Test
    fun `주변의 지뢰가 없는 일반 셀을 클릭하면 open 해야하는 cell 리스트를 얻을 수 있다`() {
        val position = Position(0, 0)
        val cells = Cells(
            listOf(
                NoneCell(Position(0, 0), 0),
                NoneCell(Position(0, 2), 0),
                NoneCell(Position(1, 0), 0),
                NoneCell(Position(0, 1), 0),
                NoneCell(Position(1, 1), 1),
                NoneCell(Position(1, 2), 1),
                NoneCell(Position(2, 0), 0),
                MineCell(Position(2, 2)),
            )
        )
        val expected = listOf(
            cells.findCell(Position(0, 1)),
            cells.findCell(Position(1, 0)),
            cells.findCell(Position(1, 1)),
            cells.findCell(Position(0, 0)),
            cells.findCell(Position(0, 2)),
            cells.findCell(Position(1, 2)),
            cells.findCell(Position(2, 0)),
        )

        val list = cells.getOpenCellList(position)

        Assertions.assertThat(list).containsAll(expected)
    }

    @Test
    fun `주변의 지뢰가 있는 일반 셀을 클릭하면 open 해야하는 cell 리스트를 얻을 수 있다`() {
        val position = Position(1, 1)
        val cells = Cells(
            listOf(
                NoneCell(Position(0, 0), 0),
                NoneCell(Position(0, 1), 0),
                NoneCell(Position(0, 2), 0),
                NoneCell(Position(1, 0), 0),
                NoneCell(Position(1, 1), 1),
                NoneCell(Position(1, 2), 1),
                NoneCell(Position(2, 0), 0),
                NoneCell(Position(2, 1), 1),
                MineCell(Position(2, 2)),
            )
        )
        val expected = listOf(
            cells.findCell(Position(1, 1))
        )

        val list = cells.getOpenCellList(position)

        Assertions.assertThat(list).isEqualTo(expected)
    }
}
