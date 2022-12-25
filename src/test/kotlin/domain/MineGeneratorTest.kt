package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll

internal class MineGeneratorTest : StringSpec({
    "높이 3 너비 3 의 보드가 있을 때 2개의 지뢰가 있다면 (1,1), (3,3)에 위치한다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(2))
        val mineLocations = listOf(0, 8)
        val generator = MineGenerator(locations = mineLocations, boardInfo = boardInfo)

        val mineCells = generator.generate()

        mineCells shouldContainAll mineCellListOf(1 to 1, 3 to 3)
    }
})
