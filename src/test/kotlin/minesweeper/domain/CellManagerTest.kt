package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Position
import minesweeper.domain.cell.toCell
import minesweeper.domain.cell.toMineCell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CellManagerTest {

    @DisplayName(value = "생성된 CellManager의 사이즈는 같아야한다.")
    @Test
    fun createTest() {
        val cells: MutableSet<Cell> = mutableSetOf(
            Position(1, 2).toCell(),
            Position(1, 2).toCell(),
            Position(1, 3).toCell()
        )
        val cellManager = CellManager.newInstance(cells)
        assertThat(cellManager.size).isEqualTo(cells.size)
    }

    @DisplayName(value = "중복 cell을 널었을땐, 사이즈가 증가하지 않는다.")
    @Test
    fun addCellTest() {
        val cellManager = CellManager.newInstance().apply {
            addCell(Position(1, 1).toCell())
            addCell(Position(1, 1).toCell())
        }
        val beforeSize = cellManager.size
        cellManager.addCell(Position(1, 1).toCell())
        assertThat(cellManager.size)
            .isEqualTo(beforeSize)
            .isEqualTo(1)
    }

    @DisplayName(value = "cell이 변경되어도, 전체 사이즈는 같아야 한다.")
    @Test
    fun changeCellTestSameSize() {
        val positions: MutableSet<Position> = mutableSetOf(
            Position(1, 2),
            Position(1, 2),
            Position(1, 3)
        )
        val cellManager = CellManager.newInstance(positions.map { it.toCell() }.toMutableSet())

        positions.map {
            cellManager.changeCell(it.toMineCell())
        }
        assertThat(cellManager.size)
            .isEqualTo(positions.size)
    }

    @DisplayName(value = "전체 open을 해도, 전체 사이즈는 같아야 한다.")
    @Test
    fun openAllTestSameSize() {
        val positions: MutableSet<Position> = mutableSetOf(
            Position(1, 2),
            Position(1, 2),
            Position(1, 3)
        )
        val cellManager = CellManager.newInstance(positions.map { it.toCell() }.toMutableSet())
        val beforeSize = cellManager.size
        cellManager.openAll()

        assertThat(beforeSize)
            .isEqualTo(positions.size)
    }
}
