package step4.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import step4.domain.CellFixture.aCell
import step4.domain.CellType.UNKNOWN
import step4.domain.CellType.ZERO

class CellTest : FunSpec({

    context("open") {
        test("이미 오픈된 좌표를 오픈하려하면 예외가 발생한다.") {
            val cell = aCell(isOpen = true)
            val exception = shouldThrowExactly<IllegalStateException> { cell.open() }
            exception shouldHaveMessage "이미 오픈된 좌표는 다시 오픈할 수 없습니다."
        }

        test("cell을 오픈한다.") {
            val cell = aCell(isOpen = false)
            cell.open()

            val actual = cell.isOpen
            actual shouldBe true
        }
    }

    context("cellType") {
        forAll(
            row(false, UNKNOWN),
            row(true, ZERO),
        ) { input, expected ->
            test("isOpen이 ${input}인 경우 cellType은 ${expected}이다.") {
                val cell = aCell(cellType = ZERO, isOpen = input)
                val actual = cell.cellType()
                actual shouldBe expected
            }
        }
    }

    context("toMine") {
        test("지뢰를 지뢰로 변경하려하면 예외가 발생한다.") {
            val cell = aCell(cellType = ZERO)
            val exception = shouldThrowExactly<IllegalStateException> { cell.toMine() }
            exception shouldHaveMessage "이미 지뢰로 변경된 cell은 다시 지뢰로 변경할 수 없습니다."
        }
    }
})

object CellFixture {
    fun aCell(
        cellType: CellType = ZERO,
        isOpen: Boolean = false,
    ) = Cell(
        cellType = cellType,
        isOpen = isOpen,
    )
}
