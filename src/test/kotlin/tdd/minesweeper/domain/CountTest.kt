package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class CountTest: FunSpec({

    test("잔여 횟수를 감소시킬 수 있으면 감소시키고 결과를 반환한다."){
        val actual = Count(1)

        actual.decreaseAndGet() shouldBe 0
    }

    test("잔여 횟수를 감소시킬 수 없으면 예외를 던진다."){
        val actual = Count(0)

        shouldThrow<IllegalArgumentException> { actual.decreaseAndGet() }
    }

    test("잔여 횟수를 확인할 수 있다."){
        val actual = Count(2)

        actual.current shouldBe 2
    }
})
