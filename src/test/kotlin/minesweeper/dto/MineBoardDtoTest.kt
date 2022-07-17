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

@DisplayName("지뢰판 dto 테스트")
class MineBoardDtoTest {

    @Test
    fun `지뢰찾기 게임 결과 생성시 지뢰가 아닌데 오픈 됐으면 주변 8개 지뢰의 개수, 닫혀 있으면 C로 표시`() {
        // given
        val `p(0,0)` = ClosedNonMine(CellPosition.of(0, 0))
        val `p(1,0)` = ClosedNonMine(CellPosition.of(1, 0))
        val `p(2,0)` = ClosedNonMine(CellPosition.of(2, 0))
        val `p(0,1)` = ClosedNonMine(CellPosition.of(0, 1))
        val `p(1,1)` = ClosedNonMine(CellPosition.of(1, 1))
        val `p(2,1)` = ClosedNonMine(CellPosition.of(2, 1))
        val `p(0,2)` = ClosedNonMine(CellPosition.of(0, 2))
        val `p(1,2)` = ClosedMine(CellPosition.of(1, 2))
        val `p(2,2)` = ClosedNonMine(CellPosition.of(2, 2))

        val board = MineBoard(
            listOf(
                Cells(mutableListOf(`p(0,0)`, `p(1,0)`, `p(2,0)`)),
                Cells(mutableListOf(`p(0,1)`, `p(1,1)`, `p(2,1)`)),
                Cells(mutableListOf(`p(0,2)`, `p(1,2)`, `p(2,2)`))
            )
        )

        // when
        board.openAtPositionAndSurroundingNonMineCells(CellPosition.of(0, 0))
        val boardResult = MineBoardDto.from(board)

        // then
        assertAll(
            "create mine board result test",
            { assertThat(boardResult.boardRows[0][0]).isEqualTo("0") },
            { assertThat(boardResult.boardRows[0][1]).isEqualTo("0") },
            { assertThat(boardResult.boardRows[0][2]).isEqualTo("0") },
            { assertThat(boardResult.boardRows[1][0]).isEqualTo("1") },
            { assertThat(boardResult.boardRows[1][1]).isEqualTo("1") },
            { assertThat(boardResult.boardRows[1][2]).isEqualTo("1") },
            { assertThat(boardResult.boardRows[2][0]).isEqualTo("C") },
            { assertThat(boardResult.boardRows[2][1]).isEqualTo("C") },
            { assertThat(boardResult.boardRows[2][2]).isEqualTo("C") },
        )
    }
}
