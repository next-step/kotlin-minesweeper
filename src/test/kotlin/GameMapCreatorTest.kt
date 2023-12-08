import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.lang.IllegalArgumentException
import java.lang.Integer.max
import java.lang.Integer.min

class GameMapCreatorTest : BehaviorSpec({
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

        `when`("valid size provided") {
            then("should create game map with exact size") {
                GameMapCreator().create(width = 2, height = 3, mineCount = 2).width shouldBe 2
                GameMapCreator().create(width = 2, height = 3, mineCount = 2).height shouldBe 3
            }
        }

        `when`("valid mine count provided") {
            then("should create game map with exact mine count") {
                GameMapCreator().create(width = 2, height = 2, mineCount = 2).mineCount() shouldBe 2
            }
        }
    }
})

class GameMapCreator {
    fun create(width: Int, height: Int, mineCount: Int): GameMap {
        require(width > 0) { "invalid game map width" }
        require(height > 0) { "invalid game map height" }
        require(mineCount > 0) { "game map should have at least 1 mine cell" }
        require(width * height > mineCount) { "mine count cannot be larger than game map size" }

        val initialCellParameters = MutableList(height) {
            MutableList(width) {
                Pair(false, 0)
            }
        }

        while (initialCellParameters.flatten().count { it.first } != mineCount) {
            val randomPosition = randomPosition(width, height)
            val row = randomPosition.second
            val col = randomPosition.first
            val cell = initialCellParameters[row][col]

            initialCellParameters[row][col] = cell.copy(first = true)
        }

        val initialGameMapScaffold = List(height) { row ->
            List(width) { col ->
                Cell(
                    isMine = initialCellParameters[row][col].first,
                    adjacentMineCount = initialCellParameters
                        .filterIndexed { rowIdx, _ -> rowIdx in max(row - 1, 0)..min(row + 1, height - 1) }
                        .map { it.slice(max(col - 1, 0)..min(col + 1, width - 1)) }
                        .flatten()
                        .count { it.first }
                )
            }
        }


        return GameMap(initialGameMapScaffold)
    }

    private fun randomPosition(width: Int, height: Int): Pair<Int, Int> =
        Pair((0 until width).random(), (0 until height).random())
}
