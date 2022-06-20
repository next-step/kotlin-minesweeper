package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class CoordinateValueTest : StringSpec({
    "x,y 좌표를 나나태는 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { CoordinateValue(1) }
    }

    "좌표를 나타내는 값은 음수가 될수 없다." {
        shouldThrow<IllegalArgumentException> { CoordinateValue(-1) }
    }
})
