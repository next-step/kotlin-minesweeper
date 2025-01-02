package domain.strategy

import domain.MineSweeperMetric
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class RandomMineCellGeneratorTest : DescribeSpec({
    lateinit var metric: MineSweeperMetric
    lateinit var sut: RandomMineCellGenerator

    describe("입력받은 정보로 셀을 생성한다.") {
        beforeTest {
            metric = MineSweeperMetric(3, 3, 3)
            sut = RandomMineCellGenerator(metric)
        }

        it("전체 셀의 개수는 height x width와 갖다.") {
            val actual = sut.execute()
            actual.size shouldBe 9
        }

        it("Cells 은 mineCount 만큼의 지뢰 셀을 갖는다.") {
            val actual = sut.execute()
            actual.filter { it.isMineCell() }.size shouldBe 3
        }

        it("Cells 은 전체 셀 - mineCount 만큼 빈 셀을 갖는다.") {
            val actual = sut.execute()
            actual.filter { it.isMineCell().not() }.size shouldBe 6
        }
    }

    describe("지뢰찾기 게임의 셀은 랜덤으로 생성된다.") {
        it("각 실행마다 다른 셀 정보를 갖는다.") {
            val cells1 = sut.execute()
            val cells2 = sut.execute()
            cells1 shouldNotBe cells2
        }
    }
})
