package step4.domain.state

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.kotest.matchers.types.shouldBeTypeOf
import step4.domain.Cell
import step4.domain.Cells
import step4.domain.Coordinate

class ReadyTest : FunSpec({

    context("init") {
        test("생성 시 toFindCellCount가 0이라면 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Ready(0, Cells()) }
            exception shouldHaveMessage "찾아야하는 cell 갯수가 0이 될 수 없습니다."
        }
    }

    context("installMine") {
        test("지뢰를 설치하고 running 상태로 변경한다.") {
            val mineCell = Cell()
            val ready = Ready(
                4,
                Cells(
                    mapOf(
                        Coordinate(0, 0) to mineCell,
                        Coordinate(0, 1) to Cell(),
                        Coordinate(1, 0) to Cell(),
                        Coordinate(1, 1) to Cell(),
                    ),
                ),
            )
            val actual = ready.installMine(1) { mineCell }
            actual.shouldBeTypeOf<Running>()
            actual.toFindCellCount shouldBe 3
        }
    }

    context("open") {
        test("준비중에 cell을 열려고하면 예외가 발생한다.") {
            val ready = Ready(1, Cells(mapOf()))
            val exception = shouldThrowExactly<IllegalStateException> { ready.open(Coordinate(0, 0)) }
            exception shouldHaveMessage "게임 시작전에 cell을 열 수 없습니다."
        }
    }

    context("isFinished") {
        test("종료상태가 아니므로 false를 반환한다.") {
            val actual = Ready(1, Cells(mapOf())).isFinished()
            actual shouldBe false
        }
    }
})
