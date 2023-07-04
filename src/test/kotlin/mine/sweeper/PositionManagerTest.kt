package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.MapSize
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width

class PositionManagerTest : StringSpec({
    "포지션 매니져는 지뢰가 설치될 랜덤 위치를 요청에 따라 전달한다" {
        val height = Height(3)
        val width = Width(3)
        val mapSize = MapSize(height, width)
        val positionManager = PositionManager(mapSize)

        val positions = positionManager.takePositionBy(MineCount(3))
        positions.size shouldBe 3
        positions.forEach {
            val isCorrectPosition = it.x in 0..width.value && it.y in 0..height.value
            isCorrectPosition shouldBe true
        }
    }
})
