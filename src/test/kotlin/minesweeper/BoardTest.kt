package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.Board.Companion.MINE_COUNT_ILLEGAL_ARGUMENTS_EXCEPTION
import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.Column
import minesweeper.domain.Height
import minesweeper.domain.MineCell
import minesweeper.domain.MineCount
import minesweeper.domain.OpenState
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.Row
import minesweeper.domain.SafetyCell
import minesweeper.domain.Width
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BoardTest {

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
    fun `Cells를 가지고 Board를 생성할 수 있다`() {
        val positions = Positions.of(Width.from(7), Height.Companion.from(7))
        val map: List<Cell> = positions.positions
            .map {
                MineCell(it)
            }

        val cells = Cells.from(map)
        val board = Board.from(cells)

        assertThat(board.cellList.size).isEqualTo(cells.cells.size)
    }

    @Test
    fun `Width, Height, MineCount를 가지고 Width * Height 크기의 Board를 생성할 수 있다`() {
        val width = Width.from(7)
        val height = Height.from(7)
        val mineCount = MineCount.from(7)

        val size = width.value * height.value
        val board = Board.ofSizeAndMineCount(width, height, mineCount)

        assertThat(board.cellList.size).isEqualTo(size)
    }

    @Test
    fun `Width, Height, MineCount를 가지고 Board를 만들 때 mineCount의 갯수는 width * height 이하여야 한다`() {
        val width = Width.from(7)
        val height = Height.from(7)
        val mineCount = MineCount.from(50)

        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Board.ofSizeAndMineCount(width, height, mineCount)
            }
            .withMessage(MINE_COUNT_ILLEGAL_ARGUMENTS_EXCEPTION)
    }

    @Test
    fun `Board는 Position에 있는 Cell이 MineCell인지 알 수 있다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)

        val isMineCell = board.isMine(Position(Row.from(0), Column.from(0)))

        assertThat(isMineCell).isTrue
    }

    @Test
    fun `Board는 게임이 끝나면 Cell을 전부 open한다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)

        val endBoard = board.endGame()

        endBoard.cellList.forEach {
            assertThat(it.openState).isInstanceOf(OpenState.OPENED::class.java)
        }
    }

    @Test
    fun `Board는 Cell이 전부 open되었는지 알 수 있다`() {
        val cellList = listOf(
            Position(Row.from(0), Column.from(0)),
            Position(Row.from(0), Column.from(1)),
            Position(Row.from(0), Column.from(2)),
            Position(Row.from(1), Column.from(0)),
            Position(Row.from(1), Column.from(1)),
            Position(Row.from(1), Column.from(2)),
        ).map { SafetyCell(it, 0, openState = OpenState.OPENED) }

        val cells = Cells.from(cellList)
        val board = Board.from(cells)
        val isSafetyCellAllOpen = board.isSafetyCellAllOpen()

        assertThat(isSafetyCellAllOpen).isTrue
    }

    @Test
    fun `Cells에서 open한 Cell이 MineCell일 때 열지 않는다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)

        val openPosition = Position(Row.from(0), Column.from(0))
        val openBoard = board.open(openPosition)

        val openState = openBoard.cellList
            .firstOrNull { it.position == openPosition }
            ?.openState

        assertThat(openState).isInstanceOf(OpenState.CLOSED::class.java)
    }

    @Test
    fun `Cells에서 open한 Cell이 SafetyCell이고, MineCell의 근처일 경우 해당 Cell만 연다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)

        val openPosition = Position(Row.from(0), Column.from(1))
        val openBoard = board.open(openPosition)

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

        openBoard.cellList.forEach {
            if (expectedOpenPositions.contains(it.position)) {
                assertThat(it.openState).isInstanceOf(OpenState.OPENED::class.java)
            }
        }

        openBoard.cellList.forEach {
            if (expectedClosePositions.contains(it.position)) {
                assertThat(it.openState).isInstanceOf(OpenState.CLOSED::class.java)
            }
        }
    }

    @Test
    fun `주변에 Mine이 없는 SafetyCell을 open할 경우 인접한 Cell이 모두 열리게 된다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)

        val openPosition = Position(Row.from(3), Column.from(0))
        val openBoard = board.open(openPosition)

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

        openBoard.cellList.forEach {
            if (expectedOpenPositions.contains(it.position)) {
                assertThat(it.openState).isInstanceOf(OpenState.OPENED::class.java)
            }
        }

        openBoard.cellList.forEach {
            if (expectedClosePositions.contains(it.position)) {
                assertThat(it.openState).isInstanceOf(OpenState.CLOSED::class.java)
            }
        }
    }
}
