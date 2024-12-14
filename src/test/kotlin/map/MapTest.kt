package map

import cell.showable.Show
import cell.status.EmptyCell
import cell.status.MineCell
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import map.move.Position
import mine.MinePoints
import open.result.OpenResult
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MapTest {
    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `지뢰찾기 지도 생성을 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size
        val map = generateGenerateTestMap(heightSize, widthSize)

        map.grid.rows.columns shouldHaveSize heightSize
        map.grid.rows.columns
            .forAll { it.points shouldHaveSize widthSize }
    }

    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `모든 초기 요소가 EmptyCell인지 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size
        val map = generateGenerateTestMap(heightSize, widthSize)

        map.grid.rows.columns
            .flatMap { it.points }
            .forAll { it.element.status shouldBe EMPTY_CELL }
    }

    @ParameterizedTest
    @MethodSource("mapSizes")
    fun `폭탄 설치 기능을 테스트한다`(point: Pair<Height, Width>) {
        val heightSize = point.first.size
        val widthSize = point.second.size
        val map = generateGenerateTestMap(heightSize, widthSize)

        val rowIndex = Index(0, heightSize)
        val columnIndex = Index(0, widthSize)
        val minePoints = MinePoints(points = listOf(Point(point = rowIndex to columnIndex)))

        map.placeMine(minePoints = minePoints)

        map.grid.rows.columns
            .flatMap { it.points }
            .filter { it.point.first == rowIndex && it.point.second == columnIndex }
            .forAll { it.element.status shouldBe MINE_CELL }
    }

    @Test
    fun `오픈한 위치가 지뢰라면 패배한다`() {
        val mineRowIndex = Index(value = 1, maxSize = MAX_SIZE)
        val mineColumnIndex = Index(value = 0, maxSize = MAX_SIZE)
        val mineCountedMap = generateTestMap(mineRowIndex, mineColumnIndex)

        mineCountedMap.open(position = Position(row = mineRowIndex, column = mineColumnIndex)) shouldBe OpenResult.MineExploded
    }

    @Test
    fun `오픈한 위치가 지롸가 아니라면 해당 칸의 숫자를 노출한다`() {
        val mineCountedMap =
            generateTestMap(
                mineRowIndex = Index(value = 1, maxSize = MAX_SIZE),
                mineColumnIndex = Index(value = 0, maxSize = MAX_SIZE),
            )

        val (openRowIndex, openColumnIndex) = Index(value = 1, maxSize = MAX_SIZE) to Index(value = 1, maxSize = MAX_SIZE)
        val openResultMap = mineCountedMap.open(position = Position(row = openRowIndex, column = openColumnIndex))

        openResultMap should beInstanceOf<OpenResult.Success>()

        val pointByIndex = getPointByIndex(openResultMap, openRowIndex, openColumnIndex)
        pointByIndex?.visibility shouldBe Show
    }

    private fun generateTestMap(
        mineRowIndex: Index,
        mineColumnIndex: Index,
    ): Map {
        val map = generateGenerateTestMap(heightSize = MAX_SIZE, widthSize = MAX_SIZE)
        val mineIndex = mineRowIndex to mineColumnIndex
        map.placeMine(MinePoints(points = listOf(Point(point = mineIndex))))
        val mineCountedMap = map.updateMineCountByCell()
        return mineCountedMap
    }

    private fun getPointByIndex(
        openResultMap: OpenResult,
        openRowIndex: Index,
        openColumnIndex: Index,
    ): Point? =
        (openResultMap as? OpenResult.Success)
            ?.map
            ?.grid
            ?.rows
            ?.getColumn(openRowIndex)
            ?.points
            ?.get(openColumnIndex.value)

    private fun generateGenerateTestMap(
        heightSize: Int,
        widthSize: Int,
    ): Map = Map.create(height = Height(size = heightSize), width = Width(size = widthSize))

    companion object {
        private const val MAX_SIZE = 3
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
