package mine.sweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > 0)
    }
}

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > 0)
    }
}

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value > 0)
    }
}

class GameOption(
    height: Height,
    width: Width,
    val mineCount: MineCount
) {
    val area = height.value * width.value
}

class GameInitializer(
    val option: GameOption
) {
    fun create(): Game {
        return Game(Fields())
    }
}

class Game(val fields: Fields) {

}

class Fields {

    val size = 25

}

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
})
