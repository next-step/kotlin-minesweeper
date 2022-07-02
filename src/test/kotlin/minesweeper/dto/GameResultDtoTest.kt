package minesweeper.dto

import minesweeper.model.Cell
import minesweeper.model.CellPosition
import minesweeper.model.Cells
import minesweeper.model.GameStatus
import minesweeper.model.MineBoard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("지뢰찾기 게임 결과 테스트")
class GameResultDtoTest {

    private lateinit var `p(0,0)`: Cell
    private lateinit var `p(1,0)`: Cell
    private lateinit var `p(2,0)`: Cell
    private lateinit var `p(0,1)`: Cell
    private lateinit var `p(1,1)`: Cell
    private lateinit var `p(2,1)`: Cell
    private lateinit var `p(0,2)`: Cell
    private lateinit var `p(1,2)`: Cell
    private lateinit var `p(2,2)`: Cell

    private lateinit var board: MineBoard

    @BeforeEach
    fun setUp() {
        // given
        // 0 0 0
        // 1 1 1
        // 1 * 1
        `p(0,0)` = Cell.nonMine(CellPosition.of(0, 0))
        `p(1,0)` = Cell.nonMine(CellPosition.of(1, 0))
        `p(2,0)` = Cell.nonMine(CellPosition.of(2, 0))
        `p(0,1)` = Cell.nonMine(CellPosition.of(0, 1))
        `p(1,1)` = Cell.nonMine(CellPosition.of(1, 1))
        `p(2,1)` = Cell.nonMine(CellPosition.of(2, 1))
        `p(0,2)` = Cell.nonMine(CellPosition.of(0, 2))
        `p(1,2)` = Cell.mine(CellPosition.of(1, 2))
        `p(2,2)` = Cell.nonMine(CellPosition.of(2, 2))

        board = MineBoard(
            listOf(
                Cells(listOf(`p(0,0)`, `p(1,0)`, `p(2,0)`)),
                Cells(listOf(`p(0,1)`, `p(1,1)`, `p(2,1)`)),
                Cells(listOf(`p(0,2)`, `p(1,2)`, `p(2,2)`))
            )
        )
    }

    @Test
    fun `지뢰찾기 게임 결과 생성시 지뢰가 아닌데 오픈 됐으면 주변 8개 지뢰의 개수, 닫혀 있으면 C로 표시`() {
        // when
        `p(0,0)`.openMeAndSurroundingNonMineCells(board)
        val boardResult = GameResultDto.from(board)

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
            { assertThat(boardResult.gameStatus).isEqualTo(GameStatus.ONGOING) }
        )
    }

    @Test
    fun `지뢰찾기 게임 결과 생성시 지뢰인데 오픈 됐으면 게임에서 진 상태`() {
        // when
        `p(1,2)`.openMeAndSurroundingNonMineCells(board)
        val boardResult = GameResultDto.from(board)

        // then
        assertThat(boardResult.gameStatus).isEqualTo(GameStatus.LOST)
    }

    @Test
    fun `지뢰찾기 게임 결과 생성시 지뢰를 제외한 모든 셀을 오픈했으면 게임에서 이긴 상태`() {
        // when
        `p(0,0)`.openMeAndSurroundingNonMineCells(board)
        `p(0,2)`.openMeAndSurroundingNonMineCells(board)
        `p(2,2)`.openMeAndSurroundingNonMineCells(board)
        val boardResult = GameResultDto.from(board)

        // then
        assertThat(boardResult.gameStatus).isEqualTo(GameStatus.WIN)
    }
}
