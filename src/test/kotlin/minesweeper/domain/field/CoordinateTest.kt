package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec

class CoordinateTest : StringSpec({
    "좌표 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Coordinate(CoordinateValue(1), CoordinateValue(2)) }
    }
})
