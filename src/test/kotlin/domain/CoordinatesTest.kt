package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CoordinatesTest : FunSpec({
    test("좌표 그룹에 어떤 좌표가 포함되는지 확인 가능하다") {
        val coordinate = Coordinate(x = 3, y = 5)
        val group = Coordinates(
            Mine(x = 1, y = 1),
            Mine(x = 2, y = 1),
            Mine(x = 3, y = 5),
        )

        (coordinate in group) shouldBe true
    }
})
