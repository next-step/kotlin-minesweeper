package minesweeper

import minesweeper.domain.Cells
import minesweeper.domain.Column
import minesweeper.domain.Height
import minesweeper.domain.MineCell
import minesweeper.domain.OpenState
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.Row
import minesweeper.domain.SafetyCell
import minesweeper.domain.Width
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {

    private lateinit var positionList: List<Position>
    private lateinit var minePositionList: List<Position>

    @BeforeEach
    internal fun setUp() {
        positionList = Positions.of(Width.from(5), Height.Companion.from(5)).positions

        minePositionList = listOf(
            Position(Row.from(0), Column.from(0)),
            Position(Row.from(1), Column.from(1)),
            Position(Row.from(1), Column.from(3)),
        )
        //  (*, C, C, C, C)
        //  (C, *, C, C, C)
        //  (C, C, C, C, C)
        //  (C, *, C, C, C)
        //  (C, C, C, C, C)
    }

    @Test
    fun `Cell List를 통해 Cells를 생성할 수 있다`() {
        val cellList = listOf(
            Position(Row.from(0), Column.from(0)),
            Position(Row.from(1), Column.from(1))
        ).map { MineCell(it) }

        val cells = Cells.from(cellList)

        assertThat(cells.cells).isEqualTo(cellList)
    }

    @Test
    fun `Position List와 Mine들의 Position List를 통 Cells를 생성할 수 있다`() {
        val cells = Cells.of(positionList, minePositionList)

        assertThat(cells.cells.size).isEqualTo(25)
    }

    @Test
    fun `Position List와 Mine들의 Position List를 통해 만든 Cells는 Mine을 가지고 있다`() {
        val cells = Cells.of(positionList, minePositionList)

        val mine1 = cells.cells.find { it.position == Position(Row.from(0), Column.from(0)) }
        val mine2 = cells.cells.find { it.position == Position(Row.from(1), Column.from(1)) }

        assertAll(
            { assertThat(mine1).isInstanceOf(MineCell::class.java) },
            { assertThat(mine2).isInstanceOf(MineCell::class.java) }
        )
    }

    @Test
    fun `Cells는 Position에 있는 셀이 Mine인지 알 수 있다`() {
        val cells = Cells.of(positionList, minePositionList)

        val isMine = cells.isMine(Position.of(Row.from(1), Column.from(1)))

        assertThat(isMine).isTrue
    }

    @Test
    fun `Cells는 Cell이 전부 SafetyCell이 전부 open되었는지 알 수 있다`() {
        val cellList = listOf(
            Position(Row.from(0), Column.from(0)),
            Position(Row.from(0), Column.from(1)),
            Position(Row.from(0), Column.from(2)),
            Position(Row.from(1), Column.from(0)),
            Position(Row.from(1), Column.from(1)),
            Position(Row.from(1), Column.from(2)),
        ).map { SafetyCell(it, 0, openState = OpenState.OPENED) }

        val cells = Cells.from(cellList)
        val isSafetyCellAllOpen = cells.isSafetyCellAllOpen()

        assertThat(isSafetyCellAllOpen).isTrue
    }

    @Test
    fun `Cells는 Cell을 전부 open할 수 있다`() {
        val cells = Cells.of(positionList, minePositionList)

        val allOpenCells = cells.cellAllOpen()
        val isAllOpen = allOpenCells.isAllOpen()

        assertThat(isAllOpen).isTrue
    }

    @Test
    fun `Cells에서 open한 Cell이 MineCell일 때 열지 않는다`() {
        val cells = Cells.of(positionList, minePositionList)

        val openPosition = Position(Row.from(0), Column.from(0))
        val openCells = cells.open(openPosition)

        val isOpen = openCells.isOpen(openPosition)

        assertThat(isOpen).isFalse
    }

    @Test
    fun `Cells에서 open한 Cell이 SafetyCell이고, MineCell의 근처일 경우 해당 Cell만 연다`() {
        val cells = Cells.of(positionList, minePositionList)

        val openPosition = Position(Row.from(0), Column.from(1))
        val openCells = cells.open(openPosition)

        val expectedOpenPositions = listOf(
            Position(Row.from(0), Column.from(1)),
        )

        val expectedClosePositions = List(5) { it }
            .flatMap { row ->
                List(5) { it }.map { column ->
                    Position(Row.from(row), Column.from(column))
                }
            }
            .filterNot {
                expectedOpenPositions.contains(it)
            }

        openCells.cells.forEach {
            if (expectedOpenPositions.contains(it.position)) {
                assertThat(it.openState).isInstanceOf(OpenState.OPENED::class.java)
            }
        }

        openCells.cells.forEach {
            if (expectedClosePositions.contains(it.position)) {
                assertThat(it.openState).isInstanceOf(OpenState.CLOSED::class.java)
            }
        }
    }

    @Test
    fun `주변에 Mine이 없는 SafetyCell을 open할 경우 인접한 Cell이 모두 열리게 된다`() {
        val cells = Cells.of(positionList, minePositionList)

        val openPosition = Position(Row.from(3), Column.from(0))
        val openCells = cells.open(openPosition)

        val expectedOpenPositions = listOf(
            Position(Row.from(2), Column.from(0)),
            Position(Row.from(3), Column.from(0)),
            Position(Row.from(4), Column.from(0)),
            Position(Row.from(2), Column.from(1)),
            Position(Row.from(3), Column.from(1)),
            Position(Row.from(4), Column.from(1)),
            Position(Row.from(2), Column.from(2)),
            Position(Row.from(3), Column.from(2)),
            Position(Row.from(4), Column.from(2)),
            Position(Row.from(2), Column.from(3)),
            Position(Row.from(3), Column.from(3)),
            Position(Row.from(4), Column.from(3)),
            Position(Row.from(2), Column.from(4)),
            Position(Row.from(3), Column.from(4)),
            Position(Row.from(4), Column.from(4)),
        )

        val expectedClosePositions = List(5) { it }
            .flatMap { row ->
                List(5) { it }.map { column ->
                    Position(Row.from(row), Column.from(column))
                }
            }
            .filterNot {
                expectedOpenPositions.contains(it)
            }

        openCells.cells.forEach {
            if (expectedOpenPositions.contains(it.position)) {
                assertThat(it.openState).isInstanceOf(OpenState.OPENED::class.java)
            }
        }

        openCells.cells.forEach {
            if (expectedClosePositions.contains(it.position)) {
                assertThat(it.openState).isInstanceOf(OpenState.CLOSED::class.java)
            }
        }
    }

    private fun Cells.isAllOpen(): Boolean {
        return cells.all { it.openState == OpenState.OPENED }
    }

    private fun Cells.isOpen(position: Position): Boolean {
        return cells.firstOrNull { it.position == position }?.isOpen() ?: throw NullPointerException()
    }
}
