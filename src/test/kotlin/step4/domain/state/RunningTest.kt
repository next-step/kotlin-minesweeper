package step4.domain.state

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import step4.domain.Cell
import step4.domain.CellType.MINE
import step4.domain.CellType.ONE
import step4.domain.Cells
import step4.domain.Coordinate

class RunningTest : FunSpec({

    context("installMine") {
        test("지뢰를 설치하려하면 예외가 발생한다.") {
            val running = Running(0, Cells(mapOf()))
            val exception = shouldThrowExactly<IllegalStateException> { running.installMine(1) { Cell() } }
            exception shouldHaveMessage "지뢰를 설치할 수 있는 상태가 아닙니다."
        }
    }

    context("open") {
        test("cell을 오픈하고 연만큼 findCellCount를 차감한다.") {
            val running = Running(
                3,
                Cells(
                    mapOf(
                        Coordinate(0, 0) to Cell(cellType = ONE),
                        Coordinate(0, 1) to Cell(cellType = ONE),
                        Coordinate(1, 0) to Cell(cellType = ONE),
                        Coordinate(1, 1) to Cell(cellType = MINE),
                    ),
                ),
            )
            running.open(Coordinate(0, 0))

            val actual = running.toFindCellCount
            actual shouldBe 2
        }
    }
})
