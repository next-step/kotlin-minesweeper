package mine.sweeper.field.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.application.MapInitializer
import mine.sweeper.application.MineSweeperGame
import mine.sweeper.application.RandomPositionManager
import mine.sweeper.domain.MineField
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.Vulture
import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class FieldsManagerTest : StringSpec({
    val height = Height(3)
    val width = Width(3)
    val mapSize = MapSize(height, width)
    "필드매니져는 필드들을 맵 전체 사이즈와 같게 생성한다." {
        val mineCount = MineCount(1)
        val vulture = Vulture(RandomPositionManager(mapSize), mineCount)
        val map = MapInitializer(mapSize, vulture.newMinePositions).create()

        map.fields.size shouldBe mapSize.area
    }

    "지뢰가 설치되면 주변 8방에 값을 1 올린다." {
        val position = Position(1, 1)
        val map = MapInitializer(mapSize, setOf(position)).create()
        val testPositions = listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2)
        )

        (map.fields[position] is MineField) shouldBe true
        testPositions.forEach {
            (map.fields[it] as SafeField).value shouldBe 1
        }
    }

    "지뢰가 있는 곳을 오픈 한다면 게임 오버 상태가 된다." {
        val map = MapInitializer(mapSize, setOf(Position(1, 1))).create()
        val game = MineSweeperGame(map)
        game.select(Position(1, 1))

        game.gameResult shouldBe GameStatus.GAME_OVER
    }
})
