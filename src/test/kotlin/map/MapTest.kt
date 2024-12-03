package map

import cell.Cell
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.api.Test

class MapTest {
    @Test
    fun `지뢰찾기 지도 생성을 테스트한다`() {
        val heightSize = 3
        val widthSize = 4
        val map = Map.create(height = Height(size = heightSize), width = Width(size = widthSize))

        map.points shouldHaveSize heightSize
        map.points.forEach { it shouldHaveSize widthSize }

        for (rowIndex in 0 until heightSize) {
            val expectedRow =
                List(widthSize) { colIndex ->
                    Point(point = Pair(rowIndex, colIndex), element = Cell)
                }
            map.points[rowIndex] shouldContainExactly expectedRow
        }
    }
}
