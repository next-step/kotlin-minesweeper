package step4.domain.state

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import step4.domain.Cell
import step4.domain.Cells
import step4.domain.Coordinate

class ReadyTest : FunSpec({

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
})
