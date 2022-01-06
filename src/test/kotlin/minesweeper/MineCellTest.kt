package minesweeper

import minesweeper.domain.Column
import minesweeper.domain.MineCell
import minesweeper.domain.OpenState
import minesweeper.domain.Position
import minesweeper.domain.Row
import minesweeper.domain.SafetyCell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MineCellTest {

    private lateinit var mineCell: MineCell

    @BeforeEach
    internal fun setUp() {
        mineCell = MineCell(position = Position.of(Row.from(1), Column(1)))
    }

    @Test
    fun `MineCell은 자기가 mine인지 알 수 있다`() {
        val isMine = mineCell.isMine()

        assertThat(isMine).isTrue
    }

    @Test
    fun `MineCell은 open()할 수 있다`() {
        val openMine = mineCell.open()

        val openState = openMine.openState

        assertThat(openState).isInstanceOf(OpenState.OPENED::class.java)
    }

    @Test
    fun `MineCell은 row가 0인지 알 수 있다`() {
        val isCellRowStart = mineCell.isRowStartCell()

        val mineCell2 = MineCell(position = Position.of(Row.from(0), Column(1)))
        val isCellRowStart2 = mineCell2.isRowStartCell()

        assertAll(
            {
                assertThat(isCellRowStart).isFalse
            },
            {
                assertThat((isCellRowStart2)).isTrue
            }
        )
    }

    @Test
    fun `MineCell은 mineCellList가 주어지면 주변(8방향)에 있는 mineCell들을 필터링 할 수 있다`() {
        val mineCellList = listOf(
            MineCell(position = Position.of(Row.from(0), Column(1))),
            MineCell(position = Position.of(Row.from(1), Column(0))),
            MineCell(position = Position.of(Row.from(1), Column(2))),
            MineCell(position = Position.of(Row.from(8), Column(8))),
            MineCell(position = Position.of(Row.from(10), Column(10))),
        )

        val aroundMineCells = mineCell.aroundMineCells(mineCellList)

        val expectedMineCellList = listOf(
            MineCell(position = Position.of(Row.from(0), Column(1))),
            MineCell(position = Position.of(Row.from(1), Column(0))),
            MineCell(position = Position.of(Row.from(1), Column(2))),
        )

        assertThat(aroundMineCells).isEqualTo(expectedMineCellList)
    }

    @Test
    fun `MineCell은 safetyCellList가 주어지면 주변(8방향)에 있는 safetyCell들을 필터링 할 수 있다`() {
        val safetyCellList = listOf(
            SafetyCell(position = Position.of(Row.from(0), Column(1)), mineCount = 0),
            SafetyCell(position = Position.of(Row.from(1), Column(0)), mineCount = 0),
            SafetyCell(position = Position.of(Row.from(1), Column(2)), mineCount = 0),
            SafetyCell(position = Position.of(Row.from(8), Column(8)), mineCount = 0),
            SafetyCell(position = Position.of(Row.from(10), Column(10)), mineCount = 0),
        )

        val aroundSafetyCells = mineCell.aroundSafetyCells(safetyCellList)

        val expectedSafetyCellList = listOf(
            SafetyCell(position = Position.of(Row.from(0), Column(1)), mineCount = 0),
            SafetyCell(position = Position.of(Row.from(1), Column(0)), mineCount = 0),
            SafetyCell(position = Position.of(Row.from(1), Column(2)), mineCount = 0),
        )

        assertThat(aroundSafetyCells).isEqualTo(expectedSafetyCellList)
    }

    @Test
    fun `MineCell은 인자로 positionList가 주어지면 주변 List만 필터링한 PositionList를 알 수 있다`() {
        val positionList = listOf(
            Position.of(Row.from(0), Column(1)),
            Position.of(Row.from(1), Column(0)),
            Position.of(Row.from(1), Column(2)),
            Position.of(Row.from(8), Column(8)),
            Position.of(Row.from(10), Column(10))
        )

        val aroundPositionList = mineCell.filterAroundCellPositions(positionList)

        val expectedPositionList = listOf(
            Position.of(Row.from(0), Column(1)),
            Position.of(Row.from(1), Column(0)),
            Position.of(Row.from(1), Column(2)),
        )

        assertThat(aroundPositionList).isEqualTo(expectedPositionList)
    }
}
