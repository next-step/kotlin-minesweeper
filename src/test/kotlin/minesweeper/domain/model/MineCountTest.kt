package minesweeper.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineCountTest : BehaviorSpec({

    given("최소 1이상을 가질 수 있는 지뢰 수가 주어지고") {
        `when`("지뢰 수에 1을 입력하면") {
            then("1을 가진 지뢰 수가 생성되어야 한다.") {
                MineCount.from(1).toInt() shouldBe 1
            }
        }
        `when`("지뢰 수에 10을 입력하면") {
            then("10을 가진 지뢰 수가 생성되어야 한다.") {
                MineCount.from(10).toInt() shouldBe 10
            }
        }
        `when`("지뢰 수에 100을 입력하면") {
            then("100을 가진 지뢰 수가 생성되어야 한다.") {
                MineCount.from(100).toInt() shouldBe 100
            }
        }
        `when`("지뢰 수에 1000을 입력하면") {
            then("1000을 가진 지뢰 수가 생성되어야 한다.") {
                MineCount.from(1000).toInt() shouldBe 1000
            }
        }

        `when`("지뢰 수에 0을 입력하면") {
            then("최소 지뢰수 보다 낮아 IllegalArgumentException 예외가 발생해야 한다.") {
                shouldThrow<IllegalArgumentException> {
                    MineCount.from(0)
                }
            }
        }
    }
})
