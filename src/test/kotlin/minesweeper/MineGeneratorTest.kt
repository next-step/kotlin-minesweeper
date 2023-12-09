package minesweeper

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.mine.MineCount
import minesweeper.mine.MineGenerator
import minesweeper.mine.Mines
import minesweeper.position.Position
import minesweeper.position.PositionGenerateStrategy

class MineGeneratorTest : BehaviorSpec({

    Given("지뢰 생성기는") {
        val generator = object : PositionGenerateStrategy {
            var index = 0
            override fun generate(): Position =
                Position(++index, ++index)
        }
        val mineGenerator = MineGenerator(MineCount(5), generator)
        When("원하는 지뢰 개수와 지뢰 생성기가 있다면") {
            Then("지뢰들을 생성한다.") {
                mineGenerator.generate() shouldBe Mines(
                    setOf(
                        Position(1, 2),
                        Position(3, 4),
                        Position(5, 6),
                        Position(7, 8),
                        Position(9, 10),
                    )
                )
            }
        }
    }
})
