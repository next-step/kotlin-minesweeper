package domain

import domain.strategy.BoardGenerateStrategy
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class BoardTest : BehaviorSpec({
    Given("보드가 주어졌을 때 ") {
        val board = Board(Height(10), Width(10), MineCnt(10))

        When("정상적인 좌표의 필드를 조회하면 ") {
            Then("정상적으로 조회한다.") {
                shouldNotThrowAny {
                    board.getField(5, 5)
                }
            }
        }

        When("비정상적인 좌표의 필드를 조회하면 ") {
            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    board.getField(13, 13)
                }
            }
        }

        val testBoard = Board(Height(10), Width(10), MineCnt(10), TestMineBoardGenerateStrategy())
        val coordinate = Coordinate(5, 5)

        When("주변에 지뢰가 있다면 ") {
            Then("정상적으로 가져온다.") {
                testBoard.getNearByMineCount(5, 5) shouldBe 6
            }
        }
    }
})

class TestMineBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(height: Height, width: Width, mineCnt: MineCnt): Fields {
        return Fields(
            (0..height.value).flatMap { h ->
                (0..width.value).map { w ->
                    val coordinate = Coordinate(h, w)
                    val field = when (h % 2 == 0) {
                        true -> Mine()
                        false -> Land()
                    }
                    coordinate to field
                }
            }.toMap()
        )
    }
}
