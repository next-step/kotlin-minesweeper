package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture.basicFields
import mine.sweeper.domain.Fields
import mine.sweeper.domain.Position
import mine.sweeper.domain.SafeField

class FieldsTest : StringSpec({
    "특정 위치의 필드를 확인한다" {
        val fields = Fields(basicFields())
        val position = Position(0, 0)
        val field = fields[position]
        field.shouldNotBeNull()
    }

    "확인한 위치가 안전 필드면서 값이 0이면 인근 안전 필드를 전부 확인한다." {
        val fields = Fields(basicFields())
        val position = Position(0, 0)
        val field = fields[position]
        field.shouldNotBeNull()
        (field is SafeField) shouldBe true
        (field as SafeField).value shouldBe 0
        field.increase()
        field.value shouldBe 1
    }
})
