package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec

class FieldTest : StringSpec({
    "지뢰 판의 필드를 생성할수 있다." {
        shouldNotThrow<Throwable> { Field(Coordinate(CoordinateValue(1), CoordinateValue(1)), Mine) }
    }
})
