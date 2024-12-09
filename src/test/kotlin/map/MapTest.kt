package map

import cell.status.EmptyCell
import cell.status.MineCell
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import mine.MinePoints
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MapTest {
    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `지뢰찾기 지도 생성을 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size
        val map = generateTestMap(heightSize, widthSize)

        map.grid.points.rows shouldHaveSize heightSize
        map.grid.points.rows
            .forAll { it.columns shouldHaveSize widthSize }
    }

    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `모든 초기 요소가 EmptyCell인지 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size
        val map = generateTestMap(heightSize, widthSize)

        map.grid.points.rows
            .flatMap { it.columns }
            .forAll { it.element.status shouldBe EMPTY_CELL }
    }

    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `폭탄 설치 기능을 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size
        val map = generateTestMap(heightSize, widthSize)

        val rowIndex = Index(0, heightSize)
        val columnIndex = Index(0, widthSize)
        val minePoints = MinePoints(points = listOf(Point(point = rowIndex to columnIndex)))

        map.placeMine(minePoints = minePoints)

        map.grid.points.rows
            .flatMap { it.columns }
            .filter { it.point.first == rowIndex && it.point.second == columnIndex }
            .forAll { it.element.status shouldBe MINE_CELL }
    }

    private fun generateTestMap(
        heightSize: Int,
        widthSize: Int,
    ): Map = Map.create(height = Height(size = heightSize), width = Width(size = widthSize))

    companion object {
        private val EMPTY_CELL = EmptyCell
        private val MINE_CELL = MineCell

        @JvmStatic
        fun mapSizes() =
            listOf(
                Pair(Height(size = 3), Width(size = 4)),
                Pair(Height(size = 5), Width(size = 5)),
                Pair(Height(size = 2), Width(size = 3)),
            )
    }
}
