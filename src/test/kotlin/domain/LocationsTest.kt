package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class LocationsTest : StringSpec({
    "셀 사이즈 만큼의 위치 리스트를 생성한다. " {
        val cellSize = 9
        val allLocations = Locations(List(cellSize) { it })

        allLocations.values.size shouldBe cellSize
    }

    "지뢰 개수 만큼 랜덤 위치 리스트를 생성한다." {
        val cellSize = 9
        val allLocations = Locations(List(cellSize) { it })
        val mineCount = MineCount(5)

        val randomLocations = allLocations.makeRandomLocations(mineCount)

        randomLocations.values.size shouldBe mineCount.value
    }

    "[1,2,3] 에서 [1,2] 를 빼면 [3] 이 남는다." {
        val locations = Locations(listOf(1, 2, 3))
        val subLocations = Locations(listOf(1, 2))

        val result = locations - subLocations
        result.values shouldBe listOf(3)
    }
})
