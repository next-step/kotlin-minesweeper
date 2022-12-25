package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class BoardInfoTest : StringSpec({
    "너비 3, 높이 3 인 보드의 범위에 포함되지 않는 좌표값이 들어오면 false 를 반환한다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(3))
        val coordinates = listOf(
            -1 to -1, 0 to 0, 0 to 1, 1 to 4, 2 to 4, 4 to 1, 4 to 4, 50 to 50
        )
        coordinates.forAll {
            val result = boardInfo.isContainRange(it.first, it.second)
            result shouldBe false
        }
    }
})
