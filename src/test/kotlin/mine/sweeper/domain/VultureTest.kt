package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width
import mine.sweeper.field.domain.FieldsInitializer

class VultureTest : StringSpec({
    "벌처가 지뢰를 선언 개수만큼 설치한다." {
        listOf(
            MineCount(5),
            MineCount(3),
            MineCount(1),
            MineCount(10),
        ).forAll { input ->
            val fields = FieldsInitializer(MapSize(Height(5), Width(5))).createFields(input)

            val count = fields.fields.count { it.value == "*" }

            count shouldBe input.value
        }
    }
})
