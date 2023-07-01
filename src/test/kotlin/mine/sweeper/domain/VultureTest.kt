package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.Width

class VultureTest : StringSpec({
    "벌처가 지뢰를 선언 개수만큼 설치한다." {
        listOf(
            5,
            3,
            1,
            10
        ).forAll { input ->
            val mapInitializer = MapInitializer(MapSize(Height(5), Width(5)))
            val map = mapInitializer.createMap()
            val vulture = Vulture(map)
            vulture.layingMines(input)
            var count = 0

            val entire = map.entireMap()
            entire.forEach { it ->
                it.filter { it.value == "*" }
                    .forEach { _ -> count += 1 }
            }

            count shouldBe input
        }
    }
})
