package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RandomMineGeneratorTest : FunSpec({
    test("랜덤지뢰생성기는 N개의 랜덤 지뢰를 생성한다") {
        val actual = RandomMineGenerator(10, 10).generate(3)

        actual.distinct().size shouldBe 3
    }

    context("랜덤지뢰생성기가 만들 수 있는 지뢰의 최대 개수는") {
        test("width x height 이다") {
            val actual = RandomMineGenerator(10, 10).generate(100)
            actual.size shouldBe 100
        }

        test("width x height 를 초과하면 예외가 발생한다") {
            shouldThrow<IllegalArgumentException> { RandomMineGenerator(10, 10).generate(101) }
        }
    }
})
