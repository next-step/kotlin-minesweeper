package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PositionTest : StringSpec({
    "올바른 좌표값으로 Position이 생성된다." {
        val position = Position(1, 2)
        position.row shouldBe 1
        position.column shouldBe 2
    }

    "원점(0,0) 좌표로 Position이 생성된다." {
        val position = Position(0, 0)
        position.row shouldBe 0
        position.column shouldBe 0
    }

    "음수 row 값으로 Position을 생성하면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Position(-1, 0)
        }
    }

    "음수 col 값으로 Position을 생성하면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Position(0, -1)
        }
    }
})
