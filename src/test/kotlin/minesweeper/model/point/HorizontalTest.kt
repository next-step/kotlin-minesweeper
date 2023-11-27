package minesweeper.model.point

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class HorizontalTest : StringSpec({

    "Horizontal 의 value 는 0과 양수만을 허용 한다 " {
        shouldNotThrow<IllegalArgumentException> {
            Horizontal(0)
            Horizontal(1)
        }
    }

    "Horizontal 의 value 에 음수인 -1 이 입력되면 throw IllegalArgumentException" {
        shouldThrow<IllegalArgumentException> {
            Horizontal(-1)
        }
    }
})
