package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("지뢰판 결과 테스트")
class MineBoardResultTest {

    @Test
    fun `지뢰판 결과 생서시 지뢰면 별표, 지뢰가 아니면 주변 8개 지뢰의 개수로 표시`() {
        // given
        // * 1 0
        // 2 2 1
        // 1 * 1
        val `p(0,0)` = Cell.mine(CellPosition.of(0, 0))
        val `p(1,0)` = Cell.nonMine(CellPosition.of(1, 0))
        val `p(2,0)` = Cell.nonMine(CellPosition.of(2, 0))
        val `p(0,1)` = Cell.nonMine(CellPosition.of(0, 1))
        val `p(1,1)` = Cell.nonMine(CellPosition.of(1, 1))
        val `p(2,1)` = Cell.nonMine(CellPosition.of(2, 1))
        val `p(0,2)` = Cell.nonMine(CellPosition.of(0, 2))
        val `p(1,2)` = Cell.mine(CellPosition.of(1, 2))
        val `p(2,2)` = Cell.nonMine(CellPosition.of(2, 2))

        val board = MineBoard(
            listOf(
                Cells(listOf(`p(0,0)`, `p(1,0)`, `p(2,0)`)),
                Cells(listOf(`p(0,1)`, `p(1,1)`, `p(2,1)`)),
                Cells(listOf(`p(0,2)`, `p(1,2)`, `p(2,2)`))
            )
        )

        // when
        val boardResult = MineBoardResult.from(board)

        // then
        assertAll(
            "create mine board result test",
            { assertThat(boardResult.boardRows[0][0]).isEqualTo("*") },
            { assertThat(boardResult.boardRows[0][1]).isEqualTo("1") },
            { assertThat(boardResult.boardRows[0][2]).isEqualTo("0") },
            { assertThat(boardResult.boardRows[1][0]).isEqualTo("2") },
            { assertThat(boardResult.boardRows[1][1]).isEqualTo("2") },
            { assertThat(boardResult.boardRows[1][2]).isEqualTo("1") },
            { assertThat(boardResult.boardRows[2][0]).isEqualTo("1") },
            { assertThat(boardResult.boardRows[2][1]).isEqualTo("*") },
            { assertThat(boardResult.boardRows[2][2]).isEqualTo("1") },
        )
    }
}
