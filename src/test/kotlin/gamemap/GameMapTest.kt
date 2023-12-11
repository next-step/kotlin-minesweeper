import gamemap.Cell
import gamemap.GameMap
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import java.lang.IllegalArgumentException

class GameMapTest : BehaviorSpec({
    given("game map") {
        `when`("invalid width") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMap(listOf(listOf()))
                }.message.shouldContain("invalid game map width")
            }
        }

        `when`("invalid height") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMap(listOf())
                }.message.shouldContain("invalid game map height")
            }
        }

        `when`("invalid mine count") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMap(listOf(listOf(Cell())))
                }.message.shouldContain("game map should have at least 1 mine cell")
            }
        }
    }
})
