package domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.MineCount

class MineCountTest : StringSpec({
    listOf(
        0 to MineCount.ZERO,
        1 to MineCount.ONE,
        2 to MineCount.TWO,
        3 to MineCount.THREE,
        4 to MineCount.FOUR,
        5 to MineCount.FIVE,
        6 to MineCount.SIX,
        7 to MineCount.SEVEN,
        8 to MineCount.EIGHT,
    ).forAll { (value, expectCount) ->
        "인접한 지뢰 개수(${value}개)로 mineCount($expectCount) 조회" {
            val result = MineCount.from(value)

            result shouldBe expectCount
        }
    }

    "1-8 의 범위를 넘어가면 조회 실패" {
        shouldThrowExactly<IllegalArgumentException> {
            MineCount.from(10)
        }
    }
})
