package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.domain.cell.CellType.Companion.toCellType
import minesweeper.domain.cell.CellType.EIGHT
import minesweeper.domain.cell.CellType.FIVE
import minesweeper.domain.cell.CellType.FOUR
import minesweeper.domain.cell.CellType.ONE
import minesweeper.domain.cell.CellType.SEVEN
import minesweeper.domain.cell.CellType.SIX
import minesweeper.domain.cell.CellType.THREE
import minesweeper.domain.cell.CellType.TWO
import minesweeper.domain.cell.CellType.ZERO

class CellTypeTest : FunSpec({

    context("parseToCellType") {
        test("지원하지 않는 셀값이 입력된 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { 9.toCellType() }
            exception shouldHaveMessage "지원하지 않는 셀 타입입니다."
        }

        forAll(
            row(0, ZERO),
            row(1, ONE),
            row(2, TWO),
            row(3, THREE),
            row(4, FOUR),
            row(5, FIVE),
            row(6, SIX),
            row(7, SEVEN),
            row(8, EIGHT),
        ) { input, expected ->
            test("${input}은 ${expected}로 변환된다.") {
                val actual = input.toCellType()
                actual shouldBe expected
            }
        }
    }
})
