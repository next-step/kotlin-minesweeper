package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture.middleMineFields
import mine.sweeper.domain.Fields
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.Position

class FieldsTest : StringSpec({
    "특정 위치의 필드를 확인한다" {
        val fields = Fields(middleMineFields())
        val position = Position(0, 0)
        val result = fields.open(position)
        result.shouldNotBeNull()
    }

    "확인한 위치가 안전 필드면서 값이 0이면 인근 안전 필드를 전부 확인한다." {
        val fields = Fields(middleMineFields())
        val position = Position(0, 0)
        val result = fields.open(position)
        result.shouldNotBeNull()
        result shouldBe GameStatus.ON_PROGRESS
    }

    "특정 위치를 확인할 때 그값이 안전하면서 주변에 하나도 지뢰가 없는 0값이면 인근 필드들 전부 오픈한다." {
        val fields = Fields(middleMineFields())
        val position = Position(0, 0)
        fields.open(position)
        fields.fieldList.filterIsInstance<SafeField>().forEach { it.checked shouldBe true }
    }
})
