package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.vo.PositiveNumber

class RandomMinesGeneratorTest : BehaviorSpec({
    given("전체 개수가 10개이고") {
        val totalCount = PositiveNumber(10)

        `when`("지뢰 수가 3개면") {
            val mineCount = PositiveNumber(3)
            val mines = RandomMinesGenerator.generate(totalCount = totalCount, mineCount = mineCount)

            then("지뢰 3개, 지뢰가 아닌것 7개를 생성한다.") {
                mines.count { it.isActive } shouldBe 3
                mines.count { it.isActive.not() } shouldBe 7
            }
        }
    }
})
