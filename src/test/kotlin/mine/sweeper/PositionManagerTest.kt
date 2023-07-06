package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.application.RandomPositionManager
import mine.sweeper.application.value.Height
import mine.sweeper.application.value.MineCount
import mine.sweeper.application.value.Width
import mine.sweeper.view.dto.MapSize

class PositionManagerTest : StringSpec({
    "포지션 매니져는 지뢰가 설치될 랜덤 위치를 요청에 따라 전달한다" {
        val height = Height(3)
        val width = Width(3)
        val mapSize = MapSize(height, width)
        val randomPositionManager = RandomPositionManager(mapSize)

        val positions = randomPositionManager.takePositionBy(MineCount(3))
        positions.size shouldBe 3
        positions.forEach {
            val isCorrectPosition = it.x in 0..width.value && it.y in 0..height.value
            isCorrectPosition shouldBe true
        }
    }
})
