package mine.sweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture.baseFields
import mine.sweeper.view.dto.Position

class FieldsTest : StringSpec({
    "필드들에서 포지션을 통해 특정 필드를 가져올 수 있다." {
        val fields = baseFields()
        val position = Position(1, 1)
        val field = fields.get(position)

        field.shouldNotBeNull()
        (field is SafeField) shouldBe true
        (field as SafeField).value shouldBe 0
    }

    "없는 위치를 요청하면 NoSuch에러를 발생시킨다" {
        val fields = baseFields()
        val wrongPosition = Position(-1, -1)
        shouldThrow<NoSuchElementException> {
            fields.get(wrongPosition)
        }
    }
})
