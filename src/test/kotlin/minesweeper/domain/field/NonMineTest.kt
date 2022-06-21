package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class NonMineTest : StringSpec({
    "지뢰가 아닌 한점을 생성할수 있다." {
        shouldNotThrow<Throwable> { NonMine.init() }
    }

    "지뢰가 아닌 경우 주변 필드의 지뢰수를 가질수 있다." {
        val nonMine = NonMine(3)
        nonMine.mineCount shouldBe 3
    }

    "주변 지뢰 갯수를 더할수 있다." {
        val nonMine = NonMine.init()

        nonMine.addCount().mineCount shouldBe 1
    }
})
