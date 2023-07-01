package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.Position
import mine.sweeper.domain.value.Width

class FieldsTest : StringSpec({
    "필드에서 지뢰를 설치하면 주변 값이 1증가한다" {
        val mapSize = MapSize(Height(3), Width(3))
        val fields = Fields(mapSize)
        val position = Position(1, 1)
        fields.changeMineField(position)

        val target = Position(0, 0)
        fields.from(target)?.value shouldBe "1"
        fields.from(position)?.value shouldBe "*"
    }

    "필드에서 지뢰를 설치하면 8방면만 값이 1증가한다" {
        val mapSize = MapSize(Height(3), Width(3))
        val fields = Fields(mapSize)
        val position = Position(0, 0)
        fields.changeMineField(position)

        val target1 = Position(0, 1)
        fields.from(target1)?.value shouldBe "1"
        val target2 = Position(1, 0)
        fields.from(target2)?.value shouldBe "1"
        val target3 = Position(1, 1)
        fields.from(target3)?.value shouldBe "1"

        val filteredFields = fields.entire().flatMap { list ->
            list.filter {
                it.value != "*" && it.value != "1"
            }
        }

        for (field in filteredFields) {
            field.value shouldBe "0"
        }
    }
})
