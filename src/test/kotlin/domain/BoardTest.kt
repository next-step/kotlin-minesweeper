package domain

import domain.strategy.BoardGenerateStrategy
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class BoardTest : BehaviorSpec({
    Given("보드가 주어졌을 때 ") {
        val board = Board(Height(10), Width(10), MineCnt(10))

        When("정상적인 보드를 조회하면 ") {
            Then("정상적으로 조회한다.") {
                shouldNotThrowAny {
                    board.getField(Coordinate(5, 5))
                }
            }
        }

        // Board (땅 O, 지뢰 X)
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        val testBoard = Board(Height(10), Width(10), MineCnt(10), TestMineBoardGenerateStrategy())

        When("주변에 지뢰가 있다면 ") {
            Then("정상적으로 가져온다.") {
                testBoard.getNearByMineCount(Coordinate(5, 5)) shouldBe 6
            }
        }

        // Board (땅 O, 지뢰 X)
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        // X O X O X O X O X O X
        val testBoard2 = Board(Height(10), Width(10), MineCnt(10), TestMineBoardGenerateStrategy())
        When("모든 땅이 열려있지 않다면 ") {
            val isGameOver = testBoard2.isGameOver()
            Then("게임이 끝나지 않았다.") {
                isGameOver shouldBe false
            }
        }

        // Board (땅 O, 지뢰 X, 열림 ㅁ)
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        // ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
        val testBoard5 = Board(Height(10), Width(10), MineCnt(10), AllOpenMineBoardGenerateStrategy())
        When("모든 땅이 열렸다면 ") {
            val isGameOver = testBoard5.isGameOver()
            Then("게임이 끝난다.") {
                isGameOver shouldBe true
            }
        }

        // Board (땅 O, 지뢰 X) -> 1,1을 열게되면 첫번째
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // X X X X X X X X X X X
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        val testBoard3 = Board(Height(10), Width(10), MineCnt(10), SecondColMineBoardGenerateStrategy())
        When("땅을 연다면 주의에 지뢰가 없다면 ") {
            testBoard3.open(Coordinate(0, 0))
            Then("인근 땅도 다 열린다.") {
                val openedLandCount = (0 until 10).sumOf { row ->
                    (0 until 10).count { col ->
                        val field = testBoard3.getField(Coordinate(row, col))
                        field is Land && field.isOpened
                    }
                }

                openedLandCount shouldBe 20
            }
        }

        // Board (땅 O, 지뢰 X)
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // X X X X X X X X X X X
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        // O O O O O O O O O O O
        val testBoard4 = Board(Height(10), Width(10), MineCnt(10), SecondColMineBoardGenerateStrategy())
        When("땅을 여는데 올바르지 않은 좌표라면 ") {
            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    testBoard4.open(Coordinate(10, 10))
                }
            }
        }
    }
})

class TestMineBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(height: Height, width: Width, mineCnt: MineCnt): Fields {
        return Fields(
            (0 until height.value).flatMap { h ->
                (0 until width.value).map { w ->
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

class AllOpenMineBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(height: Height, width: Width, mineCnt: MineCnt): Fields {
        return Fields(
            (0 until height.value).flatMap { h ->
                (0 until width.value).map { w ->
                    val coordinate = Coordinate(h, w)
                    coordinate to Land(isOpened = true)
                }
            }.toMap()
        )
    }
}

class SecondColMineBoardGenerateStrategy : BoardGenerateStrategy {
    override fun generate(height: Height, width: Width, mineCnt: MineCnt): Fields {
        return Fields(
            (0 until height.value).flatMap { h ->
                (0 until width.value).map { w ->
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
