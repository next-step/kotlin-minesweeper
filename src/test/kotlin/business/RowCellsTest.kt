package business

import business.CardVisibilityState.VISIBLE
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RowCellsTest {

    private lateinit var rowCells: RowCells

    @BeforeEach
    fun setUp() {
        rowCells = RowCells(Cell(CellStatus.EMPTY), Cell(CellStatus.MINE), Cell(CellStatus.EMPTY))
    }

    @Test
    fun `row의 범위를 가져온다`() {
        // when
        val range = rowCells.indices

        // then
        range shouldBe 0..2
    }

    @Test
    fun `특정 위치의 cell를 반환한다`() {
        // when
        val cell = rowCells[1]

        // then
        cell shouldBe Cell(CellStatus.MINE)
    }

    @Test
    fun `특정 위치의 cell이 mine이면 true`() {
        // when
        val isMine = rowCells.isMine(1)

        // then
        isMine shouldBe true
    }

    @Test
    fun `특정 위치의 cell이 mine이 아니면 false`() {
        // when
        val isMine = rowCells.isMine(0)

        // then
        isMine shouldBe false
    }

    @Test
    fun `특정 위치의 cell를 open하고 새로운 RowCell를 반환한다`() {
        // when
        val newCells = rowCells.open(0)

        // then
        newCells shouldBe RowCells(
            Cell(CellStatus.EMPTY, VISIBLE),
            Cell(CellStatus.MINE),
            Cell(CellStatus.EMPTY)
        )
        newCells shouldNotBe rowCells
    }

    @Test
    fun `모든 위치의 cell이 clear하지 않으면 false`() {
        // when
        val isAllOpen = rowCells.isAllOpen()

        // then
        isAllOpen shouldBe false
    }

    @Test
    fun `모든 위치의 cell이 clear하면 true`() {
        // given
        val newCells = rowCells.open(0).open(2)

        // when
        val isAllOpen = newCells.isAllOpen()

        // then
        isAllOpen shouldBe true
    }

    @Test
    fun `모든 셀을 순환하면서 특정 action을 한다`() {
        // given
        val action: (Cell, Point) -> Unit = { cell, point ->
            when (point) {
                Point(0, 0) -> cell shouldBe Cell(CellStatus.EMPTY)
                Point(0, 1) -> cell shouldBe Cell(CellStatus.MINE)
                Point(0, 2) -> cell shouldBe Cell(CellStatus.EMPTY)
            }
        }

        // when, then
        rowCells.processEachCellAndPoint(0, action)
    }
}
