package model.minemark

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

@DisplayName("지뢰 개수")
class MineCountTest : StringSpec({

    "0 또는 양수로 생성" {
        listOf(0, 1, Int.MAX_VALUE).forAll {
            shouldNotThrowAny {
                MineCount(it)
            }
        }
    }

    "반드시 0 이상" {
        listOf(Int.MIN_VALUE, -1).forAll {
            shouldThrowExactly<IllegalArgumentException> {
                MineCount(it)
            }
        }
    }

    "주어진 값대로 반환" {
        listOf(0, 1, Int.MAX_VALUE).forAll {
            MineCount(it).count shouldBe it
        }
    }
})
