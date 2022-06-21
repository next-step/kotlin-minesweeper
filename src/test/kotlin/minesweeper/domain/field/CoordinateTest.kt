package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll

class CoordinateTest : StringSpec({
    "좌표 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Coordinate(CoordinateValue(1), CoordinateValue(2)) }
    }

    "주변 좌표 값를 찾을수 있다." {
        val coordinate = create(1, 1)
        val findAroundCoordinates = coordinate.findAround()
        val aroundCoordinates = listOf(
            create(0, 0),
            create(0, 1),
            create(0, 2),
            create(1, 0),
            create(1, 2),
            create(2, 1),
            create(2, 0),
            create(2, 2)
        )

        findAroundCoordinates shouldContainAll aroundCoordinates
    }
}) {
    companion object {
        fun create(x: Int, y: Int): Coordinate = Coordinate(CoordinateValue(x), CoordinateValue(y))
    }
}
