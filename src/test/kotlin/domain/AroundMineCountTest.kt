package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class AroundMineCountTest : FunSpec({

    test("주변 지뢰 개수를 생성한다") {
        AroundMineCount.of(8) shouldBe AroundMineCount.EIGHT
    }

    context("주변 지뢰 개수를 생성할 때 0~8 범위가 아니면 예외가 발생한다") {
        withData(-1, 9) {
            shouldThrow<IllegalStateException> { AroundMineCount.of(it) }
        }
    }
})
