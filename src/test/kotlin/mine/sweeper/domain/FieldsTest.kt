package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture.createMineMap
import mine.sweeper.application.MapInitializer
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.Width
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class FieldsTest : StringSpec({
    "필드들에서 포지션을 통해 특정 필드를 가져올 수 있다." {
        val mapSize = MapSize(Height(3), Width(3))
        val map = MapInitializer(mapSize, setOf(Position(1, 1))).create()
        val mineField = map.fields[Position(1, 1)]
        val safeField = map.fields[Position(0, 0)]

        mineField.shouldNotBeNull()
        (mineField is MineField) shouldBe true
        safeField.shouldNotBeNull()
        (safeField is SafeField) shouldBe true
        (safeField as SafeField).value shouldBe 1
    }

    "없는 위치를 요청하면 에러 없이 null을 리턴한다" {
        val fields = createMineMap().fields
        val wrongPosition = Position(-1, -1)
        val get = fields[wrongPosition]
        get.shouldBeNull()
    }
})
