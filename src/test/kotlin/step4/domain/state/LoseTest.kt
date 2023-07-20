package step4.domain.state

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage
import step4.domain.Cell
import step4.domain.Cells
import step4.domain.Coordinate

class LoseTest : FunSpec({

    context("installMine") {
        test("지뢰를 설치하려하면 예외가 발생한다.") {
            val lose = Lose(Cells())
            val exception = shouldThrowExactly<IllegalStateException> { lose.installMine(1) { Cell() } }
            exception shouldHaveMessage "지뢰를 설치할 수 있는 상태가 아닙니다."
        }
    }

    context("open") {
        test("종료 상태에서 cell을 열려고하면 예외가 발생한다.") {
            val lose = Lose(Cells())
            val exception = shouldThrowExactly<IllegalStateException> { lose.open(Coordinate(0, 0)) }
            exception shouldHaveMessage "종료 상태에선 cell을 열 수 없습니다."
        }
    }
})