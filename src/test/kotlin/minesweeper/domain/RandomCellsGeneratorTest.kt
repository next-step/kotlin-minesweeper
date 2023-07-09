package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.vo.PositiveNumber

class RandomCellsGeneratorTest : BehaviorSpec({
    given("전체 셀의 개수가 10개이고") {
        val totalCount = PositiveNumber(10)

        `when`("지뢰 수가 3개면") {
            val mineCount = PositiveNumber(3)
            val cells = RandomCellsGenerator.generate(totalCount = totalCount, mineCount = mineCount)

            then("지뢰를 가진 셀 3개, 지뢰가 없는 셀 7개를 생성한다.") {
                cells.count { it.hasMine } shouldBe 3
                cells.count { it.hasMine.not() } shouldBe 7
            }
        }
    }
})
