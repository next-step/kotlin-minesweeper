package step4.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class CellTest : FunSpec({

    context("open") {
        test("이미 오픈된 좌표를 오픈하려하면 예외가 발생한다.") {
            val cell = Cell(cellType = CellType.ZERO, isOpen = true)
            val exception = shouldThrowExactly<IllegalStateException> { cell.open() }
            exception shouldHaveMessage "이미 오픈된 좌표는 다시 오픈할 수 없습니다."
        }
    }
})
