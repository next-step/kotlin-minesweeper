import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import java.lang.IllegalArgumentException

class GameMapCreatorTest : BehaviorSpec ({
    given("came map creator") {
        `when`("invalid width") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMapCreator().create(width = 0, height = 12, mineCount = 1)
                }.message.shouldContain("invalid game map width")
            }
        }

        `when`("invalid height") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMapCreator().create(width = 10, height = 0, mineCount = 1)
                }.message.shouldContain("invalid game map height")
            }
        }

        `when`("mine count 0 or less") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMapCreator().create(width = 2, height = 2, mineCount = 0)
                }.message.shouldContain("game map should have at least 1 mine cell")
            }
        }

        `when`("invalid mine count") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMapCreator().create(width = 2, height = 2, mineCount = 5)
                }.message.shouldContain("mine count cannot be larger than game map size")
            }
        }
    }
})

class GameMapCreator {
    fun create(width: Int, height: Int, mineCount: Int) : GameMap {
        require(width > 0) { "invalid game map width" }
        require(height > 0) { "invalid game map height" }
        require(mineCount > 0) { "game map should have at least 1 mine cell" }
        require(width * height > mineCount) { "mine count cannot be larger than game map size" }
        return GameMap(
            listOf(listOf(Cell()))
        )
    }
}
