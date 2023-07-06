package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class SizeTest : StringSpec({

    "사이즈의 행과 열은 모두 0보다 크면 예외가 발생하지 않는다." {
        shouldNotThrowAny {
            Size(1, 2)
        }
    }

    "사이즈의 행이 0보다 같거나 작으면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Size(-1, 2)
        }
    }

    "사이즈의 열이 0보다 같거나 작으면 예외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Size(2, -1)
        }
    }
})
