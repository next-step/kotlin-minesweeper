package mine.sweeper.field.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.application.FieldsManager
import mine.sweeper.application.value.GameStatus
import mine.sweeper.application.value.Height
import mine.sweeper.application.value.Width
import mine.sweeper.domain.MineField
import mine.sweeper.domain.SafeField
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class FieldsManagerTest : StringSpec({
    val height = Height(3)
    val width = Width(3)
    val mapSize = MapSize(height, width)
    "필드매니져는 필드들을 맵 전체 사이즈와 같게 생성한다." {
        val mineFields = setOf(Position(1, 0), Position(0, 1))
        val fields = FieldsManager(mapSize).create(mineFields)

        fields.fields.size shouldBe mapSize.area()
    }

    "지뢰가 설치되면 주변 8방에 값을 1 올린다." {
        val position = Position(1, 1)
        val mineFields = setOf(position)
        val fields = FieldsManager(mapSize).create(mineFields)
        val testPositions = listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        )

        (fields.get(position) is MineField) shouldBe true
        testPositions.forEach {
            (fields.get(it) as SafeField).value shouldBe 1
        }
    }

    "그리드 오픈을 통해 지뢰가 없는 인접한 칸이 모두 열리게 된다.  " {
        val fieldsManager = FieldsManager(mapSize)
        val position = Position(1, 1)
        val status = fieldsManager.gridOpen(position)
        val fields = fieldsManager.toField()

        status shouldBe GameStatus.ON_PROGRESS
        fields.fields.forEach {
            if (!it.isSame(position)) it.checked shouldBe true
        }
    }

    "지뢰가 있는 곳을 오픈 한다면 게임 오버 상태가 된다." {
        val fieldsManager = FieldsManager(mapSize)
        val position = Position(1, 1)
        fieldsManager.create(setOf(position))
        val field = fieldsManager.findOrNull(position)!!
        val status = fieldsManager.open(field)

        status shouldBe GameStatus.GAME_OVER
    }
})
