package tdd.minesweeper.domain.type

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AreaTest : FunSpec({
    test("유효한 너비와 높이를 전달하면 정상적으로 생성된다.") {
        val actual = Area(5, 5)

        actual.size shouldBe 25
    }

    test("유효하지 않은 높이나 너비를 전달하면 예외를 던진다.") {
        shouldThrow<IllegalArgumentException> {
            Area(-1, 5)
        }

        shouldThrow<IllegalArgumentException> {
            Area(5, -1)
        }
    }
})
