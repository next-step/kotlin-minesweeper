package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class MineSweeperMapTest : StringSpec({
    val option = MapSetup(height = 10, width = 10)
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
})
