package minesweeper.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class WidthTest : BehaviorSpec({

    given("최소 2이상, 최대 1000이하를 가질 수 있는 넓이가 주어지고") {
        `when`("넓이에 2를 입력하면") {
            then("2를 가진 넓이가 생성되어야 한다.") {
                Width.from(2)
            }
        }
        `when`("넓이에 500를 입력하면") {
            then("500를 가진 넓이가 생성되어야 한다.") {
                Width.from(500)
            }
        }
        `when`("넓이에 1000를 입력하면") {
            then("1000를 가진 넓이가 생성되어야 한다.") {
                Width.from(1000)
            }
        }

        `when`("넓이에 0을 입력하면") {
            then("넓이의 최소값 보다 낮아 IllegalArgumentException 예외가 발생해야 한다.") {
                shouldThrow<IllegalArgumentException> {
                    Width.from(0)
                }
            }
        }
        `when`("넓이에 1을 입력하면") {
            then("넓이의 최소값 보다 낮아 IllegalArgumentException 예외가 발생해야 한다.") {
                shouldThrow<IllegalArgumentException> {
                    Width.from(1)
                }
            }
        }
        `when`("넓이에 1001을 입력하면") {
            then("넓이의 최소값 보다 높아 IllegalArgumentException 예외가 발생해야 한다.") {
                shouldThrow<IllegalArgumentException> {
                    Width.from(1001)
                }
            }
        }
    }
})
