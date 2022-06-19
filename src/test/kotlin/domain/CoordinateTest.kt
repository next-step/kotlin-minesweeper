package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import vo.Point

internal class CoordinateTest : StringSpec({

    "좌표를 생성한다" {
        val coordinate = Coordinate(Point(1), Point(2))

        coordinate.x shouldBe Point(1)
        coordinate.y shouldBe Point(2)
    }
})
