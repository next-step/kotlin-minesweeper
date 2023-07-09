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
    private val width: Width,
    private val mineCount: MineCount
) {
    init {
        check(height.value * width.value > mineCount.value)
    }
    val area = height.value * width.value

    private val randomPositions = MutableList(area) { positionBy(it) }.shuffled().toMutableList()

    val minePositions = randomPositions.take(mineCount.value).toSet()

    fun positionBy(index: Int): Position {
        val x = index / width.value
        val y = index % width.value
        return Position(x, y)
    }
}

class GameInitializer(
    private val option: GameOption
) {
    fun create(): Game {
        val minePositions = option.minePositions

        val fieldList: MutableList<Field> = MutableList(option.area) { index ->
            val position = option.positionBy(index)
            minePositions.find { it == position }?.let { Field(it, "mine") } ?: Field(position, "safe")
        }

        return Game(Fields(fieldList))
    }
}

class Game(val fields: Fields) {

}

class Fields(val fieldList: List<Field>) {

    val size = 25

}

class Field(val position: Position, val value: String) {
}

data class Position(val x: Int, val y: Int) {

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

    "랜덤한 위치에 지뢰를 설치한다." {
        val option = GameOption(Height(2), Width(2), MineCount(1))
        val game: Game = GameInitializer(option).create()
        game.fields.fieldList.count { it.value == "mine" } shouldBe 1
        game.fields.fieldList.count { it.value == "safe" } shouldBe 3
    }

    "랜덤한 위치들에 지뢰를 설치한다." {
        val option = GameOption(Height(2), Width(2), MineCount(3))
        val game: Game = GameInitializer(option).create()
        game.fields.fieldList.count { it.value == "mine" } shouldBe 3
        game.fields.fieldList.count { it.value == "safe" } shouldBe 1
    }
})
