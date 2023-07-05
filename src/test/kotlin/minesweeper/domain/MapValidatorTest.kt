package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MapValidatorTest : StringSpec({
    "입력받은 rows나 cols이 0보다 같거나 작으면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            MapValidator.validate(-1, 0)
        }.message shouldBe "row나 col이 0보다 작습니다."
    }

    "입력받은 rows와 cols이 정상적이면 예외가 발생하지 않는다" {
        shouldNotThrowAny {
            MapValidator.validate(3, 3,)
        }
    }
})
