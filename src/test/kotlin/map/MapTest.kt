package map

import cell.Cell
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MapTest {
    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `지뢰찾기 지도 생성을 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size

        val map = Map.create(height = Height(size = heightSize), width = Width(size = widthSize))

        map.grid.points shouldHaveSize heightSize
        map.grid.points.forEach { it shouldHaveSize widthSize }

        for (rowIndex in 0 until heightSize) {
            val expectedRow =
                List(widthSize) { colIndex ->
                    Point(point = Pair(rowIndex, colIndex), element = Cell)
                }
            map.grid.points[rowIndex] shouldContainExactly expectedRow
        }
    }

    companion object {
        @JvmStatic
        fun mapSizes() =
            listOf(
                Pair(Height(size = 3), Width(size = 4)),
                Pair(Height(size = 5), Width(size = 5)),
                Pair(Height(size = 1), Width(size = 1)),
            )
    }
}
