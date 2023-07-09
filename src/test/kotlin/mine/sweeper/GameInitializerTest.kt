package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import mine.sweeper.application.FieldsInitializer
import mine.sweeper.application.Game
import mine.sweeper.application.GameInitializer
import mine.sweeper.domain.MineField
import mine.sweeper.domain.RandomGameOption
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width

class GameInitializerTest : StringSpec({
    "게임 생성자는 높이 너비 마인수에 따라 맵을 생성한다." {
        val option = RandomGameOption(Height(5), Width(5), MineCount(1))
        val fieldList = FieldsInitializer(option).create()
        val game: Game = GameInitializer(fieldList).create()
        game.shouldNotBeNull()
    }

    "높이, 너비에 따라 필드들을 생성한다." {
        val option = RandomGameOption(Height(5), Width(5), MineCount(1))
        val fieldList = FieldsInitializer(option).create()
        val game: Game = GameInitializer(fieldList).create()
        game.fields.size shouldBe option.area
    }

    "랜덤한 위치에 지뢰를 설치한다." {
        val option = RandomGameOption(Height(2), Width(2), MineCount(1))
        val fieldList = FieldsInitializer(option).create()
        val game: Game = GameInitializer(fieldList).create()
        game.fields.fieldList.count { it is MineField } shouldBe 1
        game.fields.fieldList.count { it is SafeField } shouldBe 3
    }

    "랜덤한 위치들에 지뢰를 설치한다." {
        val option = RandomGameOption(Height(2), Width(2), MineCount(3))
        val fieldList = FieldsInitializer(option).create()
        val game: Game = GameInitializer(fieldList).create()
        game.fields.fieldList.count { it is MineField } shouldBe 3
        game.fields.fieldList.count { it is SafeField } shouldBe 1
    }

    "마인 주변에는 8방의 값에 1이 증가해야한다." {
        listOf(
            MineCount(1),
            MineCount(2),
            MineCount(3)
        ).forAll { input ->
            val option = RandomGameOption(Height(2), Width(2), input)
            val fieldList = FieldsInitializer(option).create()
            val game: Game = GameInitializer(fieldList).create()
            game.fields.fieldList.filterIsInstance<SafeField>()
                .forEach { it.value shouldBe input.value }
        }
    }
})
