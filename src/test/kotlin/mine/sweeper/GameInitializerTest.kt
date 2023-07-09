package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import mine.sweeper.domain.Game
import mine.sweeper.domain.GameInitializer
import mine.sweeper.domain.GameOption
import mine.sweeper.domain.MineField
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width

class GameInitializerTest : StringSpec({
    "게임 생성자는 높이 너비 마인수에 따라 맵을 생성한다." {
        val option = GameOption(Height(5), Width(5), MineCount(1))
        val game: Game = GameInitializer(option).create()
        game.shouldNotBeNull()
    }

    "높이, 너비에 따라 필드들을 생성한다." {
        val option = GameOption(Height(5), Width(5), MineCount(1))
        val game: Game = GameInitializer(option).create()
        game.fields.size shouldBe option.area
    }

    "랜덤한 위치에 지뢰를 설치한다." {
        val option = GameOption(Height(2), Width(2), MineCount(1))
        val game: Game = GameInitializer(option).create()
        game.fields.fieldList.count { it is MineField } shouldBe 1
        game.fields.fieldList.count { it is SafeField } shouldBe 3
    }

    "랜덤한 위치들에 지뢰를 설치한다." {
        val option = GameOption(Height(2), Width(2), MineCount(3))
        val game: Game = GameInitializer(option).create()
        game.fields.fieldList.count { it is MineField } shouldBe 3
        game.fields.fieldList.count { it is SafeField } shouldBe 1
    }

    "마인 주변에는 8방의 값에 1이 증가해야한다." {
        listOf(
            MineCount(1),
            MineCount(2),
            MineCount(3)
        ).forAll { input ->
            val option = GameOption(Height(2), Width(2), input)
            val game: Game = GameInitializer(option).create()
            game.fields.fieldList.filterIsInstance<SafeField>()
                .forEach { it.value shouldBe input.value }
        }
    }
})
