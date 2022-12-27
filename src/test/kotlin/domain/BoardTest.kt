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

        val testBoard2 = Board(Height(10), Width(10), MineCnt(10), TestMineBoardGenerateStrategy())
        When("모든 땅이 열려있지 않다면 ") {
            val isGameOver = testBoard2.isGameOver()
            Then("게임이 끝나지 않았다.") {
                isGameOver shouldBe false
            }
        }

        When("모든 땅이 열렸다면 ") {
            (0..10).forEach() { h ->
                (0..10).forEach() { w ->
                    when (val field = testBoard2.getField(h, w)) {
                        is Land -> field.open()
                        is Mine -> Unit
                    }
                }
            }
            val isGameOver = testBoard2.isGameOver()
            Then("게임이 끝난다.") {
                isGameOver shouldBe true
            }
        }

        val testBoard3 = Board(Height(10), Width(10), MineCnt(10), SecondColMineBoardGenerateStrategy())
        When("땅을 연다면 주의에 지뢰가 없다면 ") {
            testBoard3.open(0, 0)
            Then("인근 땅도 다 열린다.") {
                val openedLandCount = (0..10).count { w ->
                    val field = testBoard3.getField(0, w)
                    field is Land && field.isOpened
                }

                openedLandCount shouldBe 11
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

class SecondColMineBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(height: Height, width: Width, mineCnt: MineCnt): Fields {
        return Fields(
            (0..height.value).flatMap { h ->
                (0..width.value).map { w ->
                    val coordinate = Coordinate(h, w)
                    val field = when (h == 2) {
                        true -> Mine()
                        false -> Land()
                    }
                    coordinate to field
                }
            }.toMap()
        )
    }
}
