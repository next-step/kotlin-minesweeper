package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ClosedTest : FunSpec({

    test("Closed 를 연다") {
        Closed.open(3) shouldBe Open(3)
    }

    test("Closed 는 주변 지뢰 개수를 반환할 때 예외가 발생한다") {
        shouldThrow<IllegalStateException> { Closed.aroundMineCount() }
    }
})
