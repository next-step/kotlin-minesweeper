package mine.sweeper.field.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.MapSize
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.Position
import mine.sweeper.domain.value.Width

class FieldsManagerTest : StringSpec({
    val height = Height(3)
    val width = Width(3)
    val mapSize = MapSize(height, width)
    "필드매니져는 필드들을 맵 전체 사이즈와 같게 생성한다." {
        val fieldsManager = FieldsManager(mapSize)

        val fields = fieldsManager.toFields()
        fields.fields.size shouldBe mapSize.area()
    }

    "필드매니져를 통해 필드의 상태를 변경한다 " {
        val fieldsManager = FieldsManager(mapSize)
        val position = Position(0, 0)
        fieldsManager.changeMineField(position)

        val fields = fieldsManager.toFields()
        fields.get(position).value shouldBe "*"
    }

    "지뢰가 설치되면 주변 8방에 값을 1 올린다." {
        val fieldsManager = FieldsManager(mapSize)
        val position = Position(1, 1)
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

        val originalFields = fieldsManager.toFields()
        testPositions.forEach {
            originalFields.get(it).value shouldBe "0"
        }

        fieldsManager.changeMineField(position)

        val updateFields = fieldsManager.toFields()
        testPositions.forEach {
            updateFields.get(it).value shouldBe "1"
        }
    }
})
