import gamemap.GameMapCreator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.lang.IllegalArgumentException

class GameMapCreatorTest : BehaviorSpec({
    given("came map creator") {
        `when`("invalid width") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMapCreator(width = 0, height = 12, mineCount = 1)
                }.message.shouldContain("invalid game map width")
            }
        }

        `when`("invalid height") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMapCreator(width = 10, height = 0, mineCount = 1)
                }.message.shouldContain("invalid game map height")
            }
        }

        `when`("mine count 0 or less") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMapCreator(width = 2, height = 2, mineCount = 0)
                }.message.shouldContain("game map should have at least 1 mine cell")
            }
        }

        `when`("invalid mine count") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    GameMapCreator(width = 2, height = 2, mineCount = 5)
                }.message.shouldContain("mine count cannot be larger than game map size")
            }
        }

        `when`("valid size provided") {
            then("should create game map with exact size") {
                GameMapCreator(width = 2, height = 3, mineCount = 2).create().width shouldBe 2
                GameMapCreator(width = 2, height = 3, mineCount = 2).create().height shouldBe 3
            }
        }

        `when`("valid mine count provided") {
            then("should create game map with exact mine count") {
                GameMapCreator(width = 2, height = 2, mineCount = 2).create().mineCount() shouldBe 2
            }
        }

        `when`("valid large mine count provided") {
            then("should create game map with exact mine count") {
                GameMapCreator(width = 5, height = 6, mineCount = 23).create().mineCount() shouldBe 23
            }
        }
    }
})
