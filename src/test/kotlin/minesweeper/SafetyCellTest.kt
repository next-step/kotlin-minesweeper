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

class SafetyCellTest {
    private lateinit var safetyCell: SafetyCell

    @BeforeEach
    internal fun setUp() {
        safetyCell = SafetyCell(position = Position.of(Row.from(1), Column(1)), mineCount = 0)
    }

    @Test
    fun `SafetyCell은 자기가 mine인지 알 수 있다`() {
        val isMine = safetyCell.isMine()

        assertThat(isMine).isFalse
    }

    @Test
    fun `SafetyCell은 open()할 수 있다`() {
        val openMine = safetyCell.open()

        val openState = openMine.openState

        assertThat(openState).isInstanceOf(OpenState.OPENED::class.java)
    }

    @Test
    fun `SafetyCell은 주변 mine의 갯수인 mineCount를 가진다`() {
        val mineCount = safetyCell.mineCount

        assertThat(mineCount).isEqualTo(0)
    }

    @Test
    fun `SafetyCell은 mineCount가 0인지 알 수 있다`() {
        val isAroundMine = safetyCell.isNotContainAroundMine()

        assertThat(isAroundMine).isTrue
    }

    @Test
    fun `SafetyCell은 row가 0인지 알 수 있다`() {
        val isCellRowStart = safetyCell.isRowStartCell()

        val safetyCell2 = SafetyCell(position = Position.of(Row.from(0), Column(1)), 0)
        val isCellRowStart2 = safetyCell2.isRowStartCell()

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
    fun `SafetyCell은 mineCellList가 주어지면 주변(8방향)에 있는 mineCell들을 필터링 할 수 있다`() {
        val mineCellList = listOf(
            MineCell(position = Position.of(Row.from(0), Column(1))),
            MineCell(position = Position.of(Row.from(1), Column(0))),
            MineCell(position = Position.of(Row.from(1), Column(2))),
            MineCell(position = Position.of(Row.from(8), Column(8))),
            MineCell(position = Position.of(Row.from(10), Column(10))),
        )

        val aroundMineCells = safetyCell.aroundMineCells(mineCellList)

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

        val aroundSafetyCells = safetyCell.aroundSafetyCells(safetyCellList)

        val expectedSafetyCellList = listOf(
            SafetyCell(position = Position.of(Row.from(0), Column(1)), mineCount = 0),
            SafetyCell(position = Position.of(Row.from(1), Column(0)), mineCount = 0),
            SafetyCell(position = Position.of(Row.from(1), Column(2)), mineCount = 0),
        )

        assertThat(aroundSafetyCells).isEqualTo(expectedSafetyCellList)
    }

    @Test
    fun `SafetyCell은 인자로 positionList가 주어지면 주변 List만 필터링한 PositionList를 알 수 있다`() {
        val positionList = listOf(
            Position.of(Row.from(0), Column(1)),
            Position.of(Row.from(1), Column(0)),
            Position.of(Row.from(1), Column(2)),
            Position.of(Row.from(8), Column(8)),
            Position.of(Row.from(10), Column(10))
        )

        val aroundPositionList = safetyCell.filterAroundCellPositions(positionList)

        val expectedPositionList = listOf(
            Position.of(Row.from(0), Column(1)),
            Position.of(Row.from(1), Column(0)),
            Position.of(Row.from(1), Column(2)),
        )

        assertThat(aroundPositionList).isEqualTo(expectedPositionList)
    }
}
