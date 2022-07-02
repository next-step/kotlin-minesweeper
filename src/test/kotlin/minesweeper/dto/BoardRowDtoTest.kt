package minesweeper.dto

import minesweeper.model.Cell
import minesweeper.model.CellPosition
import minesweeper.model.Cells
import minesweeper.model.MineBoard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("보드 한줄 테스트")
class BoardRowDtoTest {

    @Test
    fun `보드 한줄의 닫힌 셀 개수 구하는 기능이 정상 동작`() {
        // given, when, then
        assertThat(BoardRowDto(listOf("*", "C", "C")).countOfCloseMark).isEqualTo(2)
    }

    @Test
    fun `보드 한줄에 지뢰 포함 여부 구하는 기능이 정상 동작`() {
        // given, when, then
        assertAll(
            "contains mine test",
            { assertThat(BoardRowDto(listOf("C", "C", "C")).containsMine()).isFalse },
            { assertThat(BoardRowDto(listOf("C", "*", "C")).containsMine()).isTrue }
        )
    }

    @Test
    fun `보드 한줄 생성하는 기능이 정상 동작`() {
        // given
        // 0 0 0
        // 1 1 1
        // 1 * 1
        val `p(0,0)` = Cell.nonMine(CellPosition.of(0, 0))
        val `p(1,0)` = Cell.nonMine(CellPosition.of(1, 0))
        val `p(2,0)` = Cell.nonMine(CellPosition.of(2, 0))
        val `p(0,1)` = Cell.nonMine(CellPosition.of(0, 1))
        val `p(1,1)` = Cell.nonMine(CellPosition.of(1, 1))
        val `p(2,1)` = Cell.nonMine(CellPosition.of(2, 1))
        val `p(0,2)` = Cell.nonMine(CellPosition.of(0, 2))
        val `p(1,2)` = Cell.mine(CellPosition.of(1, 2))
        val `p(2,2)` = Cell.nonMine(CellPosition.of(2, 2))

        val cellsOfRow1 = Cells(listOf(`p(0,0)`, `p(1,0)`, `p(2,0)`))
        val cellsOfRow2 = Cells(listOf(`p(0,1)`, `p(1,1)`, `p(2,1)`))
        val cellsOfRow3 = Cells(listOf(`p(0,2)`, `p(1,2)`, `p(2,2)`))

        val board = MineBoard(listOf(cellsOfRow1, cellsOfRow2, cellsOfRow3))
        `p(0,0)`.openMeAndSurroundingNonMineCells(board)

        // when, then
        assertAll(
            "create board row test",
            { assertThat(BoardRowDto.of(cellsOfRow1, board).boardRow).isEqualTo(listOf("0", "0", "0")) },
            { assertThat(BoardRowDto.of(cellsOfRow2, board).boardRow).isEqualTo(listOf("1", "1", "1")) },
            { assertThat(BoardRowDto.of(cellsOfRow3, board).boardRow).isEqualTo(listOf("C", "C", "C")) },
        )
    }
}
