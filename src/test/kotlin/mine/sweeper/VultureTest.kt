package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class VultureTest : StringSpec({
    "벌처가 지뢰를 선언 개수만큼 설치한다." {
        listOf(
            5,
            3,
            1,
            10,
        ).forAll { input ->
            val vulture = Vulture(input)
            val option = SweeperMapSetup(height = 10, width = 10)
            val map = SweeperMap(option)
            vulture.layingMines(map)
            var count = 0

            val entire = map.entireMap()
            entire.forEach { it ->
                it.filter { it == Field.MINE_FIELD }
                    .forEach { _ -> count += 1 }
            }

            count shouldBe input
        }
    }
})
