package domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.CellMark
import java.lang.IllegalArgumentException

class CellMarkTest : StringSpec({
    listOf(
        0 to CellMark.ZERO,
        1 to CellMark.ONE,
        2 to CellMark.TWO,
        3 to CellMark.THREE,
        4 to CellMark.FOUR,
        5 to CellMark.FIVE,
        6 to CellMark.SIX,
        7 to CellMark.SEVEN,
        8 to CellMark.EIGHT,
    ).forAll { (adjacentMineCount, expectMark) ->
        "인접한 지뢰 개수(${adjacentMineCount}개)로 mark($expectMark) 조회" {
            val result = CellMark.from(adjacentMineCount)

            result shouldBe expectMark
        }
    }

    "1-8 의 범위를 넘어가면 조회 실패" {
        shouldThrowExactly<IllegalArgumentException> {
            CellMark.from(10)
        }
    }
})
