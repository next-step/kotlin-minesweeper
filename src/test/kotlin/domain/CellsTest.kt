package domain

import domain.strategy.RandomMineCellGenerator
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellsTest : DescribeSpec({
    describe("generate test") {
        lateinit var mineGameMetric: MineGameMetric
        beforeTest { mineGameMetric = MineGameMetric(3, 3, 1) }

        it("지뢰를 생성하고 남은 칸의 수는 비어있는 칸이다.") {
            val sut = Cells.generateWithMines(mineGameMetric, RandomMineCellGenerator())
            sut.emptyCells().size shouldBe 8
        }

        it("지뢰 위치만큼 지뢰 칸을 차지한다.") {
            val sut = Cells.generateWithMines(mineGameMetric, RandomMineCellGenerator())
            sut.mineCells().size shouldBe 1
        }
    }
})
