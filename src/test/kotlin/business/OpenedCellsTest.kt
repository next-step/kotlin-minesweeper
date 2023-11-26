package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class OpenedCellsTest {
    @Test
    fun `open위치를 추가한다`() {
        // given
        val openedCells = OpenedCells(listOf(Point(1, 1)))
        val point = Point(1, 2)

        // when
        openedCells.add(point)

        // then
        openedCells.openedPoints shouldBe listOf(Point(1, 1), Point(1, 2))
    }
}
