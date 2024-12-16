package minecount.strategy

import element.Mine
import io.kotest.matchers.shouldBe
import map.Grid
import map.Height
import map.Index
import map.Rows
import map.Width
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class SurroundingMinesTest {
    private lateinit var rows: Rows
    private lateinit var grid: Grid
    private lateinit var mineCountStrategy: MineCountStrategy

    @BeforeEach
    fun beforeEach() {
        val height = Height(SIZE)
        val width = Width(SIZE)
        rows = Rows.ready(height = height, width = width)
        mineCountStrategy = SurroundingMines(points = rows)
        grid = Grid(rows = rows, mineCountStrategy = mineCountStrategy)
        grid.place(
            Index(value = MINE_INDEX, maxSize = height.size),
            Index(value = MINE_INDEX, maxSize = width.size),
            element = Mine.ready(),
        )
    }

    @ParameterizedTest
    @MethodSource("points")
    fun `탐색한 위치에 인접한 지뢰의 개수를 반환한다`(
        rowIndex: Int,
        columnIndex: Int,
        expectedCount: Int,
    ) {
        val searchRow = Index(value = rowIndex, maxSize = SIZE)
        val searchColumn = Index(value = columnIndex, maxSize = SIZE)

        mineCountStrategy.calculate(searchRow, searchColumn) shouldBe expectedCount
    }

    @Test
    fun `폭탄을 탐색할 위치가 없으면 0을 반환한다`() {
        mineCountStrategy.calculate(null, null) shouldBe 0
    }

    companion object {
        private const val SIZE = 5
        private const val MINE_INDEX = 3
        private const val MINE_ROW = 2
        private const val MINE_COLUMN = 2
        private const val NO_MINE_ROW = 0
        private const val NO_MINE_COLUMN = 0
        private const val EXPECTED_ADJACENT_MINE_COUNT = 1
        private const val EXPECTED_NO_ADJACENT_MINE_COUNT = 0

        @JvmStatic
        fun points() =
            listOf(
                Arguments.of(MINE_ROW, MINE_COLUMN, EXPECTED_ADJACENT_MINE_COUNT),
                Arguments.of(NO_MINE_ROW, NO_MINE_COLUMN, EXPECTED_NO_ADJACENT_MINE_COUNT),
            )
    }
}
