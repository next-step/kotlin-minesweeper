package minesweeper.model.point

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class VerticalTest : StringSpec({

    "Vertical 의 value 는 0과 양수만을 허용 한다 " {
        shouldNotThrow<IllegalArgumentException> {
            Vertical(0)
            Vertical(1)
            Vertical(100)
            Vertical(99999)
        }
    }

    "Vertical 의 value 에 음수가 입력되면 throw IllegalArgumentException" {
        shouldThrow<IllegalArgumentException> {
            Vertical(-1)
            Vertical(-2)
        }
    }
})
