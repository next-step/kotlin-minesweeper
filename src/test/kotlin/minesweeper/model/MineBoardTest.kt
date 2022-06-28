package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("지뢰판 테스트")
class MineBoardTest {

    private lateinit var `p(0,0)`: Cell
    private lateinit var `p(1,0)`: Cell
    private lateinit var `p(2,0)`: Cell
    private lateinit var `p(3,0)`: Cell

    private lateinit var `p(0,1)`: Cell
    private lateinit var `p(1,1)`: Cell
    private lateinit var `p(2,1)`: Cell
    private lateinit var `p(3,1)`: Cell

    private lateinit var `p(0,2)`: Cell
    private lateinit var `p(1,2)`: Cell
    private lateinit var `p(2,2)`: Cell
    private lateinit var `p(3,2)`: Cell

    private lateinit var `p(0,3)`: Cell
    private lateinit var `p(1,3)`: Cell
    private lateinit var `p(2,3)`: Cell
    private lateinit var `p(3,3)`: Cell

    private lateinit var board: MineBoard

    @BeforeEach
    fun setUp() {
        // given
        // 1 * 1 0
        // 1 2 2 1
        // 0 1 * 1
        // 0 1 1 1
        `p(0,0)` = Cell.nonMine(CellPosition.of(0, 0))
        `p(1,0)` = Cell.mine(CellPosition.of(1, 0))
        `p(2,0)` = Cell.nonMine(CellPosition.of(2, 0))
        `p(3,0)` = Cell.nonMine(CellPosition.of(3, 0))

        `p(0,1)` = Cell.nonMine(CellPosition.of(0, 1))
        `p(1,1)` = Cell.nonMine(CellPosition.of(1, 1))
        `p(2,1)` = Cell.nonMine(CellPosition.of(2, 1))
        `p(3,1)` = Cell.nonMine(CellPosition.of(3, 1))

        `p(0,2)` = Cell.nonMine(CellPosition.of(0, 2))
        `p(1,2)` = Cell.nonMine(CellPosition.of(1, 2))
        `p(2,2)` = Cell.mine(CellPosition.of(2, 2))
        `p(3,2)` = Cell.nonMine(CellPosition.of(3, 2))

        `p(0,3)` = Cell.nonMine(CellPosition.of(0, 3))
        `p(1,3)` = Cell.nonMine(CellPosition.of(1, 3))
        `p(2,3)` = Cell.nonMine(CellPosition.of(2, 3))
        `p(3,3)` = Cell.nonMine(CellPosition.of(3, 3))

        board = MineBoard(
            listOf(
                Cells(listOf(`p(0,0)`, `p(1,0)`, `p(2,0)`, `p(3,0)`)),
                Cells(listOf(`p(0,1)`, `p(1,1)`, `p(2,1)`, `p(3,1)`)),
                Cells(listOf(`p(0,2)`, `p(1,2)`, `p(2,2)`, `p(3,2)`)),
                Cells(listOf(`p(0,3)`, `p(1,3)`, `p(2,3)`, `p(3,3)`)),
            )
        )
    }

    @Test
    fun `지뢰판이 정상적으로 생성`() {
        // given
        val boardCreateDto = MineBoardCreateDto(width = 5, height = 4, mineCount = 3)

        // when
        val mineBoard = MineBoard.of(boardCreateDto).board

        // then
        mineBoard.forEach { assertThat(it.size).isEqualTo(5) }
        assertThat(mineBoard.size).isEqualTo(4)
        assertThat(mineBoard.sumOf { it.mineCount }).isEqualTo(3)
    }

    @Test
    fun `p(0,0)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(0,0)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(0,0) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(true) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(2,0)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(2,0)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(2,0) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(true) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(3,0)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(3,0)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(3,0) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(true) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(true) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(true) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(true) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(0,1)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(0,1)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(0,1) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(true) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(1,1)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(1,1)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(1,1) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(2,1)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(2,1)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(2,1) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(true) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(3,1)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(3,1)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(3,1) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(true) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(0,2)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(0,2)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(0,2) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(true) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(true) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(true) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(1,2)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(1,2)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(1,2) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(3,2)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(3,2)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(3,2) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(true) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(0,3)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(0,3)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(0,3) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(true) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(true) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(true) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(1,3)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(1,3)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(1,3) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(true) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(2,3)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(2,3)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(2,3) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(true) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(false) },
        )
    }

    @Test
    fun `p(3,3)를 기준으로 지뢰가 없는 인접한 칸이 모두 열리는 기능이 정상 동작`() {
        // when
        `p(3,3)`.openMeAndSurroundingNonMineCells(board)

        // then
        assertAll(
            "p(3,3) open me and surrounding non mine cells test",
            { assertThat(`p(0,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,0)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,0)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,1)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,1)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,2)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,2)`.isOpened).isEqualTo(false) },

            { assertThat(`p(0,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(1,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(2,3)`.isOpened).isEqualTo(false) },
            { assertThat(`p(3,3)`.isOpened).isEqualTo(true) },
        )
    }
}
