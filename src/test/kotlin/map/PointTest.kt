package map

import cell.Cell
import io.kotest.matchers.shouldBe
import mine.Mine
import minecount.strategy.SurroundingMines
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `해당 위치가 지뢰면 true를 반환한다`() {
        val point = Point(point = mock to mock, element = Mine.ready())
        point.isMine() shouldBe true
    }

    @Test
    fun `해당 위치가 지뢰가 아니면 false를 반환한다`() {
        val point = Point(point = mock to mock, element = Cell.ready())
        point.isMine() shouldBe false
    }

    @Test
    fun `Point의 요소를 주위에 위치한 지뢰 개수로 변경한다`() {
        val height = Height(SIZE)
        val width = Width(SIZE)
        val rows = Rows.ready(height = height, width = width)

        val mineCountStrategy = SurroundingMines(points = rows)
        rows.rows[MINE_INDEX].columns[MINE_INDEX] =
            Point(
                point =
                    Pair(
                        Index(value = MINE_INDEX, maxSize = height.size),
                        Index(value = MINE_INDEX, maxSize = width.size),
                    ),
                element = Mine.ready(),
            )

        val point =
            Point(
                point =
                    Pair(
                        Index(value = MINE_INDEX - 1, maxSize = height.size),
                        Index(value = MINE_INDEX - 1, maxSize = width.size),
                    ),
                element = Cell.ready(),
            )
        val updatedPoint =
            point.updateWithAdjacentMineCount { rowIndex, columnIndex ->
                mineCountStrategy.calculate(rowIndex, columnIndex)
            }

        updatedPoint.element.value shouldBe "1"
    }

    companion object {
        private const val SIZE = 10
        private const val MINE_INDEX = 0
        private val mock = null
    }
}
