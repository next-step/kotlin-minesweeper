package business

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class OpenedCellsTest {
    @Test
    fun `open위치를 추가한다`() {
        // given
        val openedCells = OpenedCells(5, 5, listOf(Point(1, 1)))
        val point = Point(1, 2)

        // when
        openedCells.add(point)

        // then
        openedCells.openedPoints shouldBe listOf(Point(1, 1), Point(1, 2))
    }

    // before
    // * C C C C
    // C 1 C C C
    // C C C C C
    // C C C C C
    // C C C C C
    // after openAround(1, 1)
    // * 0 0 C C
    // 0 1 0 C C
    // 0 0 0 C C
    // C C C C C
    // C C C C C
    @Test
    fun `주변에 지뢰가 없는 위치를 open한다`() {
        // given
        val openedCells = OpenedCells(5, 5, listOf(Point(1, 1)))
        val mines = Mines(listOf(Mine(Point(0, 0))))

        // when
        openedCells.openAround(Point(1, 1), mines)

        // then
        openedCells.openedPoints shouldContainExactlyInAnyOrder listOf(
            Point(1, 0),
            Point(1, 1),
            Point(1, 2),
            Point(0, 1),
            Point(0, 2),
            Point(2, 0),
            Point(2, 1),
            Point(2, 2)
        )
    }
}
