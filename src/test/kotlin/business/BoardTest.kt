package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/**
 *  testCells
 *  | 1 | * | C | C |
 *  | C | C | C | C |
 *  | C | C | C | C |
 *  | C | C | C | C |
 *  | C | C | C | * |
 */
class BoardTest {
    @Test
    fun `특정 위치에 지뢰이면 ture`() {
        // given
        val board = Board(testCells())

        // when
        val result = board.isMine(Point(0, 1))

        // then
        result shouldBe true
    }

    @Test
    fun `특정 위치에 지뢰가 아니면 false`() {
        // given
        val board = Board(testCells())

        // when
        val result = board.isMine(Point(0, 0))

        // then
        result shouldBe false
    }

    @Test
    fun `특정 위치의 주변에 지뢰가 없으면 0`() {
        // given
        val board = Board(testCells())

        // when
        val result = board.countMines(Point(2, 0))

        // then
        result shouldBe 0
    }

    @Test
    fun `특정 위치의 주변에 지뢰가 개수를 계산한다`() {
        // given
        val board = Board(testCells())

        // when
        val result = board.countMines(Point(1, 0))

        // then
        result shouldBe 1
    }

    @Test
    fun `특정 위치가 open이면 true`() {
        // given
        val board = Board(testCells())

        // when
        val result = board.isOpen(Point(0, 0))

        // then
        result shouldBe true
    }

    @Test
    fun `특정 위치가 open이 아니면 false`() {
        // given
        val board = Board(testCells())

        // when
        val result = board.isOpen(Point(1, 0))

        // then
        result shouldBe false
    }

    @Test
    fun `지뢰 위치를 open하면 실패 결과를 반환한다`() {
        // given
        val board = Board(testCells())

        // when
        val result = board.open(Point(0, 1))

        // then
        result shouldBe GameStatus.GAME_OVER
    }

    @Test
    fun `지뢰가 아니고 주변에 지뢰가 있는 위치를 open하면 해당 위치를 open하고 continue 결과를 반환한다`() {
        // given
        val board = Board(testCells())
        val targetPoint = Point(1, 0)

        // when
        val result = board.open(targetPoint)

        // then
        result shouldBe GameStatus.CONTINUE
        board.isOpen(targetPoint) shouldBe true
    }

    /**
     *  | C | * | C | C |                          | C | * | 1 | 0 |
     *  | C | C | C | C |                          | 1 | 1 | 1 | 0 |
     *  | C | C | C | C |      =>  open(2,1)  =>   | 0 | 0 | 0 | 0 |
     *  | C | C | C | C |                          | 0 | 0 | 1 | 1 |
     *  | C | C | C | * |                          | 0 | 0 | 1 | * |
     */
    @Test
    fun `지뢰가 아니고 주변에 지뢰가 없는 위치를 open하면 해당 위치를 open하고 주변도 모두 open하며 continue 결과를 반환한다`() {
        // given
        val board = Board(
            testCells()
        )
        val targetPoint = Point(2, 1)

        // when
        val result = board.open(targetPoint)

        // then
        result shouldBe GameStatus.CONTINUE
        shouldBeExpected(board, targetPoint)
    }

    private fun shouldBeExpected(board: Board, targetPoint: Point) {
        board.isOpen(targetPoint) shouldBe true
        board.isOpen(Point(0, 2)) shouldBe true
        board.isOpen(Point(0, 3)) shouldBe true
        board.isOpen(Point(1, 0)) shouldBe true
        board.isOpen(Point(1, 1)) shouldBe true
        board.isOpen(Point(1, 2)) shouldBe true
        board.isOpen(Point(1, 3)) shouldBe true
        board.isOpen(Point(2, 0)) shouldBe true
        board.isOpen(Point(2, 2)) shouldBe true
        board.isOpen(Point(2, 3)) shouldBe true
        board.isOpen(Point(3, 0)) shouldBe true
        board.isOpen(Point(3, 1)) shouldBe true
        board.isOpen(Point(3, 2)) shouldBe true
        board.isOpen(Point(3, 3)) shouldBe true
        board.isOpen(Point(4, 0)) shouldBe true
        board.isOpen(Point(4, 1)) shouldBe true
        board.isOpen(Point(4, 2)) shouldBe true
    }

    /**
     *  | 1 | * | C | C |
     *  | C | C | C | C |
     *  | C | C | C | C |
     *  | C | C | C | C |
     *  | C | C | C | * |
     */
    private fun testCells() = Cells(
        listOf(
            listOf(
                Cell(CellStatus.EMPTY, CardVisibilityState.VISIBLE),
                Cell(CellStatus.MINE),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY)
            ),
            listOf(
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.EMPTY),
                Cell(CellStatus.MINE)
            ),
        )
    )
}
