package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

internal class LocationsTest : StringSpec({
    "셀 사이즈가 넘는 범위의 리스트를 받으면 에러가 발생한다." {
        val cellSize = 9
        val list = listOf(-1, 1, 3, 5, 8, 10, 12, 13)

        shouldThrow<IndexOutOfBoundsException> {
            Locations.from(cellSize, list)
        }
    }

    "셀 사이즈 범위에 포함되는 리스트는 Locations 인스턴스를 생성한다." {
        val cellSize = 9
        val list = listOf(0, 2, 3, 4, 5)

        val locations = Locations.from(cellSize, list)

        locations.shouldBeInstanceOf<Locations>()
    }

    "[1,2,3] 에서 [1,2] 를 빼면 [3] 이 남는다." {
        val locations = Locations(listOf(1, 2, 3))
        val subLocations = Locations(listOf(1, 2))

        val result = locations - subLocations
        result.values shouldBe listOf(3)
    }
})
