package mine.sweeper.field.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.MapSize
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width

class FieldsInitializerTest : StringSpec({
    "필드 생성자에서 필드들을 생성할 수 있다!" {
        val height = Height(3)
        val width = Width(3)
        val mapSize = MapSize(height, width)
        val fieldsInitializer = FieldsInitializer(mapSize)

        val createFields = fieldsInitializer.createFields(MineCount(3))
        createFields.fields.size shouldBe 9
    }
})
