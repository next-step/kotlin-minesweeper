package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : StringSpec({
    val option = MapOption(height = 10, width = 10)
    "높이와 너비에 따라 빈 땅을 생성한다" {
        val map = SweeperMap(option)

        val entire = map.entireMap()
        entire.shouldNotBeNull()
    }

    "최초의 구역들은 전부다 안전 구역이다." {
        val map = SweeperMap(option)

        val entire = map.entireMap()
        entire.forEach {
            it.forEach { field ->
                field shouldBe Field.SAFE_FIELD
            }
        }
    }

    "벌처가 지뢰를 선언 개수만큼 설치한다." {
        listOf(
            5,
            3,
            1,
            10,
        ).forAll { input ->
            val vulture = Vulture(input)
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
