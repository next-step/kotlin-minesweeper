package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.Width
import mine.sweeper.field.domain.FieldsManager

class FieldsTest : StringSpec({
    "필드에서 지뢰를 설치하면 주변 값이 1증가한다" {
        val mapSize = MapSize(Height(3), Width(3))
        val manager = FieldsManager(mapSize)
        val position = Position(1, 1)
        manager.changeMineField(position)
        val target = Position(0, 0)
        val toFields = manager.toFields()
        toFields.get(target).value shouldBe "1"
        toFields.get(position).value shouldBe "*"
    }

    "필드에서 지뢰를 설치하면 8방면만 값이 1증가한다" {
        val mapSize = MapSize(Height(3), Width(3))
        val manager = FieldsManager(mapSize)
        val position = Position(0, 0)
        manager.changeMineField(position)
        val toFields = manager.toFields()

        val target1 = Position(0, 1)
        toFields.get(target1).value shouldBe "1"
        val target2 = Position(1, 0)
        toFields.get(target2).value shouldBe "1"
        val target3 = Position(1, 1)
        toFields.get(target3).value shouldBe "1"

        val filteredFields = toFields.fields.filter {
            it.value != "*" && it.value != "1"
        }

        for (field in filteredFields) {
            field.value shouldBe "0"
        }
    }
})
