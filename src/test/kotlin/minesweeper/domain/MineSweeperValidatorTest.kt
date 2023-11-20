package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperValidatorTest : BehaviorSpec({

    given("지뢰판(2x2)와 인덱스(3,2)가 주어졌을때") {
        val mineSweeperMapIndex = MineSweeperMap(2, 2).createPosition()
        val mineSweeperIndex = MineSweeperIndex(Position(3, 2))
        When("유효성 검사를 할 때") {
            val exception = shouldThrow<IllegalArgumentException> {
                MineSweeperValidator.validate(mineSweeperIndex, mineSweeperMapIndex)
            }
            Then("에러를 발생한다.") {
                exception.message shouldBe "지뢰 찾기 범위를 벗어났습니다."
            }
        }
    }

    given("지뢰판(2x2)와 인덱스(1,1)가 주어졌을때") {
        val mineSweeperMapIndex = MineSweeperMap(2, 2).createPosition()
        val mineSweeperIndex = MineSweeperIndex(Position(1, 1))
        When("유효성 검사를 할 때") {
            val result = MineSweeperValidator.validate(mineSweeperIndex, mineSweeperMapIndex)
            Then("유효하여 인덱스를 반환한다.") {
                result.position shouldBe mineSweeperIndex.position
            }
        }
    }
})
