package business

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class OpenedCellsTest {
    // O - Opened C - Closed * - Mine
    // before                        after
    // * C C C C                     * C C C C
    // C C C C C                     C O C C C
    // C C C C C   openAround(1, 1)  C C C C C
    // C C C C C                     C C C C C
    // C C C C C                     C C C C C
    @Test
    fun `open위치를 추가한다`() {
        // given
        val openedCells = OpenedCells.of(5, 5)
        val mines = Mines(listOf(Mine(Point(0, 0))))

        // when
        openedCells.add(Point(1, 1), mines)

        // then
        openedCells.openedPoints shouldBe listOf(Point(1, 1))
    }

    // O - Opened C - Closed * - Mine
    // before                         after
    // * C C * C                      * C C * C
    // C O C C *                      O O O C *
    // C C C * C   openAround(3, 1)   O O O * C
    // C C C C C                      O O O C C
    // C C C * C                      O O O * C
    @Test
    fun `주변에 지뢰가 없는 위치를 open한다`() {
        // given
        val openedCells = OpenedCells.of(5, 5, listOf(Point(1, 1)))
        val mines = `주변에 지뢰가 없는 위치를 open 테스트 시나리오 지뢰`()

        // when
        openedCells.add(Point(3, 1), mines)

        // then
        openedCells.openedPoints shouldContainExactlyInAnyOrder `주변에 지뢰가 없는 위치를 open 테스트 예상결과`()
    }

    @Test
    fun `포인트를 포함하는지 확인한다`() {
        // given
        val openedCells = OpenedCells.of(5, 5, listOf(Point(1, 1)))

        // when
        val contains = openedCells.contains(Point(1, 1))

        // then
        contains shouldBe true
    }

    @Test
    fun `모든 지뢰가 open되었는지 확인한다`() {
        // given
        val openedCells = OpenedCells.of(
            5, 5,
            listOf(Point(1, 0), Point(1, 1), Point(1, 2), Point(1, 3), Point(1, 4))
        )
        val needOpenedSize = 20

        // when
        val isAllOpened = openedCells.isAllOpened(needOpenedSize)

        // then
        isAllOpened shouldBe true
    }

    @Test
    fun `모든 지뢰가 open되지 않았는지 확인한다`() {
        // given
        val openedCells = OpenedCells.of(
            5, 5,
            listOf(Point(1, 1), Point(1, 2), Point(1, 3), Point(1, 4))
        )
        val needOpenedSize = 5

        // when
        val isAllOpened = openedCells.isAllOpened(needOpenedSize)

        // then
        isAllOpened shouldBe false
    }

    @Test
    fun `범위를 초과한 point로 openedCells 생성하면 예외를 던진다`() {
        // given
        val height = 5
        val width = 5
        val openedPoints = listOf(Point(1, 5))

        // when, then
        shouldThrow<IllegalArgumentException> {
            OpenedCells.of(
                height,
                width,
                openedPoints
            )
        }.message shouldBe "범위를 초과한 point가 존재한다"
    }

    private fun `주변에 지뢰가 없는 위치를 open 테스트 시나리오 지뢰`() = Mines(
        listOf(
            Mine(Point(0, 0)),
            Mine(Point(0, 3)),
            Mine(Point(1, 4)),
            Mine(Point(2, 3)),
            Mine(Point(4, 3))
        )
    )

    private fun `주변에 지뢰가 없는 위치를 open 테스트 예상결과`() = listOf(
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
