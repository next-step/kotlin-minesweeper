package map

import cell.Cell
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import mine.Mine
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MapTest {
    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `지뢰찾기 지도 생성을 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size
        val map = generateTestMap(heightSize, widthSize)

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

    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `폭탄 설치 기능을 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size
        val map = generateTestMap(heightSize, widthSize)

        val minePoint = 0 to 0
        map.placeMine(point = Point(point = minePoint))

        map.grid.points shouldHaveSize heightSize
        map.grid.points.forEach { it shouldHaveSize widthSize }
        map.grid.points[minePoint.first][minePoint.second].element shouldBe Mine
    }

    private fun generateTestMap(
        heightSize: Int,
        widthSize: Int,
    ): Map = Map.create(height = Height(size = heightSize), width = Width(size = widthSize))

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
