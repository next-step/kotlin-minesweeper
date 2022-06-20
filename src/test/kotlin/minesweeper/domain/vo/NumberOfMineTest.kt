package minesweeper.domain.vo

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class NumberOfMineTest : StringSpec({
    "지뢰 갯수를 나타내는 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { NumberOfMine(1) }
    }

    "지뢰 갯수가 음수일 경우 Exception을 던진다." {
        shouldThrow<IllegalArgumentException> { NumberOfMine(-1) }
    }
})
