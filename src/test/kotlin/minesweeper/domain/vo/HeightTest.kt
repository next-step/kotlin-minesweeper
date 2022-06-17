package minesweeper.domain.vo

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll

class HeightTest : StringSpec({
    "높이를 나타내는 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Height(1) }
    }

    "높이가 양수가 아닐 경우 Exception을 던진다." {
        listOf(0, -1).forAll {
            shouldThrow<IllegalArgumentException> { Height(it) }
        }
    }
})
