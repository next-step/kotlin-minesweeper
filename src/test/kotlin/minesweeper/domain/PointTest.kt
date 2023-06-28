package minesweeper.domain

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

class PointTest: ShouldSpec({
    should("값이 같은 두 Point는 동일 하다.") {
        Point(2, 3) shouldBe Point(2, 3)
    }
})
