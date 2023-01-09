package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll

internal class CellGeneratorTest : StringSpec({
    /**
     * 0 1 X
     * X 4 5
     * 6 7 8
     */
    "3 x 3 보드가 있을 때 2개의 지뢰가 있고 2, 3 의 위치에 존재한다면 (1,3), (2,1) 셀에 지뢰가 생성된다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val mineLocations = locationsOf(2, 3)
        val blankLocations = locationsOf(0, 1, 4, 5, 6, 7, 8)
        val cells = CellGenerator(mineLocations, blankLocations, boardInfo.row).generate()

        cells shouldContainAll mineCellListOf(1 to 3, 2 to 1)
    }

    /**
     * 0 1 X
     * X 4 5
     * 6 7 8
     */
    "3 x 3 보드가 있을 때 2개의 지뢰가 있고 2, 3 의 위치에 지뢰셀을 생성하면 나머지 셀은 빈칸이다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val mineLocations = locationsOf(2, 3)
        val blankLocations = locationsOf(0, 1, 4, 5, 6, 7, 8)
        val cells = CellGenerator(mineLocations, blankLocations, boardInfo.row).generate()

        cells shouldContainAll blankCellListOf(1 to 1, 1 to 2, 2 to 2, 2 to 3, 3 to 1, 3 to 2, 3 to 3)
    }
})
