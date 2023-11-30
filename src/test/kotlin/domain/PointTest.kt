package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PointTest : FunSpec({
    test("Point는 두 Point를 더할 수 있다") {
        val point1 = Point(1, 2)
        val point2 = Point(3, -4)
        val result = point1 + point2
        result shouldBe Point(4, -2)
    }

    test("Point로 넓이를 구할 수 있다") {
        val point = Point(3, 4)
        point.getArea() shouldBe 12
    }
})
