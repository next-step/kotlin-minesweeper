package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PositionTest : StringSpec({
    "유효한 row와 col 값을 가질 수 있다." {
        val position = Position(1, 2)
        position.row shouldBe 1
        position.col shouldBe 2
    }

    "row나 col이 음수이면 예외를 발생시킨다." {
        shouldThrow<IllegalArgumentException> { Position(-1, 0) }
        shouldThrow<IllegalArgumentException> { Position(0, -1) }
    }
})
