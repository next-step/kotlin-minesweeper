package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BlankGeneratorTest : StringSpec({
    /**
     * 1 X 1
     * 1 1 1
     * 0 0 0
     */
    "3x3 보드에 (1,2) 좌표에 지뢰가 있다면 1 지뢰 카운트를 가진 빈칸이 5개, 0 지뢰 카운트를 가진 빈칸이 3개이다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(1))
        val board = Board(mineCellListOf(1 to 2))
        val blankLocations = listOf(0, 2, 3, 4, 5, 6, 7, 8)

        val blankCells = BlankGenerator(blankLocations, boardInfo, board).generate()
        val countOne = blankCells.count { it.minesAroundCount == 1 }
        val countZero = blankCells.count { it.minesAroundCount == 0 }

        countOne shouldBe 5
        countZero shouldBe 3
    }

    /**
     * 1 X 1
     * 1 2 2
     * 0 1 X
     */
    "3x3 보드에 (1,2), (3,3) 좌표에 지뢰가 있다면 2 지뢰 카운트를 가진 빈칸 2개, 1 지뢰 카운트를 가진 빈칸 4개, 0 지뢰 카운트를 가진 빈칸이 1개이다. " {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val board = Board(mineCellListOf(1 to 2, 3 to 3))
        val blankLocations = listOf(0, 2, 3, 4, 5, 6, 7)

        val blankCells = BlankGenerator(blankLocations, boardInfo, board).generate()
        val countTwo = blankCells.count { it.minesAroundCount == 2 }
        val countOne = blankCells.count { it.minesAroundCount == 1 }
        val countZero = blankCells.count { it.minesAroundCount == 0 }

        countTwo shouldBe 2
        countOne shouldBe 4
        countZero shouldBe 1
    }
})
