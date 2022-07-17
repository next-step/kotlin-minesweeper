package minesweeper.dto

import minesweeper.model.CellPosition
import minesweeper.model.Cells
import minesweeper.model.ClosedMine
import minesweeper.model.ClosedNonMine
import minesweeper.model.MineBoard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("보드 한줄 테스트")
class MineBoardRowDtoTest {

    @Test
    fun `보드 한줄 생성하는 기능이 정상 동작`() {
        // given
        // 0 0 0
        // 1 1 1
        // 1 * 1
        val `p(0,0)` = ClosedNonMine(CellPosition.of(0, 0))
        val `p(1,0)` = ClosedNonMine(CellPosition.of(1, 0))
        val `p(2,0)` = ClosedNonMine(CellPosition.of(2, 0))
        val `p(0,1)` = ClosedNonMine(CellPosition.of(0, 1))
        val `p(1,1)` = ClosedNonMine(CellPosition.of(1, 1))
        val `p(2,1)` = ClosedNonMine(CellPosition.of(2, 1))
        val `p(0,2)` = ClosedNonMine(CellPosition.of(0, 2))
        val `p(1,2)` = ClosedMine(CellPosition.of(1, 2))
        val `p(2,2)` = ClosedNonMine(CellPosition.of(2, 2))

        val cellsOfRow1 = Cells(mutableListOf(`p(0,0)`, `p(1,0)`, `p(2,0)`))
        val cellsOfRow2 = Cells(mutableListOf(`p(0,1)`, `p(1,1)`, `p(2,1)`))
        val cellsOfRow3 = Cells(mutableListOf(`p(0,2)`, `p(1,2)`, `p(2,2)`))

        val board = MineBoard(listOf(cellsOfRow1, cellsOfRow2, cellsOfRow3))
        board.openAtPositionAndSurroundingNonMineCells(CellPosition.of(0, 0))

        // when, then
        assertAll(
            "create board row test",
            { assertThat(MineBoardRowDto.of(cellsOfRow1, board).boardRow).isEqualTo(listOf("0", "0", "0")) },
            { assertThat(MineBoardRowDto.of(cellsOfRow2, board).boardRow).isEqualTo(listOf("1", "1", "1")) },
            { assertThat(MineBoardRowDto.of(cellsOfRow3, board).boardRow).isEqualTo(listOf("C", "C", "C")) },
        )
    }
}
