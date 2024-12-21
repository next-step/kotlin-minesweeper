package domain.strategy

import domain.MineGameMetric
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class RandomMineCellGeneratorTest : DescribeSpec({
    describe("RandomMineCellGenerator") {
        it("지뢰 게수만큼 지뢰 셀을 생성한다") {
            val mineGameMetric = MineGameMetric(10, 10, 3)
            val sut = RandomMineCellGenerator()

            val actual = sut.execute(mineGameMetric)
            actual.size shouldBe 3
        }
    }
})
