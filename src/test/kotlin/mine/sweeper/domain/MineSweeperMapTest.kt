package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width
import mine.sweeper.field.domain.FieldsInitializer
import mine.sweeper.field.domain.FieldsManager
import mine.sweeper.field.domain.SafeField

class MineSweeperMapTest : StringSpec({
    val mapSize = MapSize(Height(5), Width(5))
    val fieldsInitializer = FieldsInitializer(mapSize)
    "높이와 너비에 따라 빈 땅을 생성한다" {
        val fields = fieldsInitializer.createFields(MineCount(5))

        fields.fields.size shouldBe 25
    }

    "최초의 구역들은 전부다 안전 구역이다." {
        val fields = FieldsManager(mapSize).toFields()

        fields.fields.forEach { field ->
            (field is SafeField) shouldBe true
        }
    }
})
