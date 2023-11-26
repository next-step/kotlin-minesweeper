package business

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class OpenedCellsTest {
    // O - Opened
    // C - Closed
    // * - Mine
    // before
    // * C C C C
    // C C C C C
    // C C C C C
    // C C C C C
    // C C C C C
    // after openAround(1, 1)
    // * C C C C
    // C O C C C
    // C C C C C
    // C C C C C
    // C C C C C
    @Test
    fun `open위치를 추가한다`() {
        // given
        val openedCells = OpenedCells(5, 5)
        val mines = Mines(listOf(Mine(Point(0, 0))))

        // when
        openedCells.add(Point(1, 1), mines)

        // then
        openedCells.openedPoints shouldBe listOf(Point(1, 1))
    }

    // O - Opened
    // C - Closed
    // * - Mine
    // before
    // * C C * C
    // C O C C *
    // C C C * C
    // C C C C C
    // C C C * C
    // after openAround(3, 1)
    // * C C * C
    // O O O C *
    // O O O * C
    // O O O C C
    // O O O * C
    @Test
    fun `주변에 지뢰가 없는 위치를 open한다`() {
        // given
        val openedCells = OpenedCells(5, 5, listOf(Point(1, 1)))
        val mines = Mines(
            listOf(
                Mine(Point(0, 0)),
                Mine(Point(0, 3)),
                Mine(Point(1, 4)),
                Mine(Point(2, 3)),
                Mine(Point(4, 3))
            )
        )

        // when
        openedCells.add(Point(3, 1), mines)

        // then
        openedCells.openedPoints shouldContainExactlyInAnyOrder listOf(
            Point(1, 0),
            Point(1, 1),
            Point(1, 2),
            Point(2, 0),
            Point(2, 1),
            Point(2, 2),
            Point(3, 0),
            Point(3, 1),
            Point(3, 2),
            Point(4, 0),
            Point(4, 1),
            Point(4, 2)
        )
    }
}
