package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import mine.sweeper.Fixture.basicFields
import mine.sweeper.domain.Fields
import mine.sweeper.domain.Position

class FieldsTest : StringSpec({
    "특정 위치의 필드를 확인한다" {
        val fields = Fields(basicFields())
        val position = Position(0, 0)
        val field = fields[position]
        field.shouldNotBeNull()
    }
})
