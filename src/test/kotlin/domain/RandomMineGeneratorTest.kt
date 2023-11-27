package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RandomMineGeneratorTest : FunSpec({
    test("랜덤지뢰생성기는 N개의 랜덤 지뢰를 생성한다") {
        val actual = RandomMineGenerator(10, 10).generate(3)

        actual.distinct().size shouldBe 3
    }

    test("랜덤지뢰생성기가 만들 수 있는 지뢰의 최대개수는 width x height 이다") {
        shouldThrow<IllegalArgumentException> { RandomMineGenerator(10, 10).generate(101) }
    }
})
