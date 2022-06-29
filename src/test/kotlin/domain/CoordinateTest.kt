package domain

import domain.vo.Point
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CoordinateTest : StringSpec({

    "좌표를 생성한다" {
        val coordinate = Coordinate(Point(1), Point(2))

        coordinate.x shouldBe Point(1)
        coordinate.y shouldBe Point(2)
    }
})
