package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CoordinateValueTest : StringSpec({
    "x,y 좌표를 나나태는 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { CoordinateValue(1) }
    }

    "좌표를 더할수 있다." {
        val coordinateValue = CoordinateValue(1)

        coordinateValue + CoordinateValue(2) shouldBe CoordinateValue(3)
    }

    "좌표를 뺼수 있다." {
        val coordinateValue = CoordinateValue(2)

        coordinateValue - CoordinateValue(1) shouldBe CoordinateValue(1)
    }
})
