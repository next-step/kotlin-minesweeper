package business

import business.TestFixture.testCells
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/**
 *  testCells
 *         0   1   2   3
 *
 *  0    | 1 | * | C | C |
 *  1    | C | C | C | C |
 *  2    | C | C | C | C |
 *  3    | C | C | C | C |
 *  4    | C | C | C | * |
 *  5    | C | C | * | C |
 */
class CellsTest {

    @Test
    fun `지뢰이면 ture`() {
        // given
        val cells = testCells()

        // when
        val result = cells.isMine(Point(0, 1))

        // then
        result shouldBe true
    }

    @Test
    fun `지뢰가 아니면 false`() {
        // given
        val cells = testCells()

        // when
        val result = cells.isMine(Point(0, 0))

        // then
        result shouldBe false
    }

    @Test
    fun `open되여 있으면 ture`() {
        // given
        val cells = testCells()

        // when
        val result = cells.isOpen(Point(0, 0))

        // then
        result shouldBe true
    }

    @Test
    fun `open되여 있지 않으면 false`() {
        // given
        val cells = testCells()

        // when
        val result = cells.isOpen(Point(0, 2))

        // then
        result shouldBe false
    }

    /**
     *  expected
     *         0   1   2   3
     *
     *  0    | 1 | * | 1 | C |
     *  1    | C | C | C | C |
     *  2    | C | C | C | C |
     *  3    | C | C | C | C |
     *  4    | C | C | C | * |
     *  5    | C | C | * | C |
     */
    @Test
    fun `지뢰가 주변에 있으면 그 위치한 open한다`() {
        // given
        val cells = testCells()

        // when
        cells.open(Point(0, 2))

        // then
        cells.isOpen(Point(0, 2)) shouldBe true
        cells.isOpen(Point(0, 3)) shouldBe false
        cells.isOpen(Point(1, 2)) shouldBe false
        cells.isOpen(Point(1, 3)) shouldBe false
        cells.isOpen(Point(1, 1)) shouldBe false
    }

    /**
     *  expected
     *         0   1   2   3
     *
     *  0    | 1 | * | 1 | 0 |
     *  1    | 1 | 1 | 1 | 0 |
     *  2    | 0 | 0 | 0 | 0 |
     *  3    | 0 | 0 | 1 | 1 |
     *  4    | 0 | 1 | 2 | * |
     *  5    | 0 | 1 | * | C |
     */
    @Test
    fun `지뢰가 주변에 없으면 그 주변도 open한다`() {
        // given
        val cells = testCells()

        // when
        cells.open(Point(2, 0))

        // then
        val expectedNotOpenedPoints = listOf(
            Point(5, 3),
        )
        expectedNotOpenedPoints.forEach {
            cells.isOpen(it) shouldBe false
        }
        for (x in 0..5) {
            for (y in 0..3) {
                if (Point(x, y) !in expectedNotOpenedPoints) {
                    cells.isOpen(Point(x, y)) shouldBe true
                }
            }
        }
    }

    @Test
    fun `주병의 지뢰 칸의 개수를 계산한다`() {
        // given
        val cells = testCells()

        // when
        val result = cells.countMines(Point(1, 0))

        // then
        result shouldBe 1
    }

    /**
     *         0   1   2   3                                   0   1   2   3
     *
     *  0    | C | * | C | C |                               | C | * | 1 | 0 |
     *  1    | C | C | C | C |                               | 1 | 1 | 1 | 0 |
     *  2    | C | C | C | C |      =>  open(2,1)(5,3)  =>   | 0 | 0 | 0 | 0 |
     *  3    | C | C | C | C |                               | 0 | 0 | 1 | 1 |
     *  4    | C | C | C | * |                               | 0 | 1 | 2 | * |
     *  5    | C | C | * | C |                               | 0 | 1 | * | 2 |
     */
    @Test
    fun `지뢰 빼고 모든 칸이 open되여 있으면 true`() {
        // given
        val cells = testCells()
        cells.open(Point(2, 0))
        cells.open(Point(2, 1))

        // when
        val result = cells.isAllOpen()

        // then
        result shouldBe false
    }

    @Test
    fun `지뢰 빼고 모든 칸이 open되여 있지 않으면 false`() {
        // given
        val cells = testCells()

        // when
        val result = cells.isAllOpen()

        // then
        result shouldBe false
    }

    /**
     *         0   1   2   3
     *
     *  0    | C | * | C | C |
     *  1    | C | C | C | C |
     *  2    | C | C | C | C |
     *  3    | C | C | C | C |
     *  4    | C | C | C | * |
     *  5    | C | C | * | C |
     */
    @Test
    fun `각 셀이 열렸는지의 여부와 주변의 지뢰의 개수를 전달한다`() {
        // given
        val cells = testCells()
        val result = mutableListOf<String>()

        // when
        cells.processEachCell(
            { cell -> result.add("${cell.isOpen()}, ${cell.aroundMineCount}") }
        ) { }

        // then
        result shouldBe listOf(
            "true, 1", "true, 0", "false, 1", "false, 0",
            "false, 1", "false, 1", "false, 1", "false, 0",
            "false, 0", "false, 0", "false, 0", "false, 0",
            "false, 0", "false, 0", "false, 1", "false, 1",
            "false, 0", "false, 1", "false, 2", "true, 1",
            "false, 0", "false, 1", "true, 1", "false, 2",
        )
    }

    /**
     *         0   1   2   3
     *
     *  0    | C | * | C | C |
     *  1    | C | C | C | C |
     *  2    | C | C | C | C |
     *  3    | C | C | C | C |
     *  4    | C | C | C | * |
     *  5    | C | C | * | C |
     */
    @Test
    fun `각 셀이 지뢰인지와 주변의 지뢰의 개수를 전달한다`() {
        // given
        val cells = testCells()
        val result = mutableListOf<String>()

        // when
        cells.processEachCell(
            { cell -> result.add("${cell.isMine()}, ${cell.aroundMineCount}") }
        ) { }

        // then
        result shouldBe listOf(
            "false, 1", "true, 0", "false, 1", "false, 0",
            "false, 1", "false, 1", "false, 1", "false, 0",
            "false, 0", "false, 0", "false, 0", "false, 0",
            "false, 0", "false, 0", "false, 1", "false, 1",
            "false, 0", "false, 1", "false, 2", "true, 1",
            "false, 0", "false, 1", "true, 1", "false, 2",
        )
    }
}
