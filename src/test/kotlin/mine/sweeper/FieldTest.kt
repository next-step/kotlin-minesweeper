package mine.sweeper

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.MineField
import mine.sweeper.domain.Position
import mine.sweeper.domain.SafeField

class FieldTest : StringSpec({
    "필드는 안전 필드, 지뢰가 설치된 필드가 있다." {
        val position = Position(0, 0)
        shouldNotThrowAny {
            SafeField(position)
            MineField(position)
        }
    }

    "안전 필드는 증가 할 수 있는 값을 가지고 있다." {
        val position = Position(0, 0)
        val safeField = SafeField(position)
        safeField.value shouldBe 0
        safeField.increase()
        safeField.value shouldBe 1
    }
})
