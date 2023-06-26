package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class MinesweeperBoardGeneratorTest : BehaviorSpec({

    Given(name = "넓이와 높이, 마인 개수가 주어지면") {
        val invalidBoardSize = BoardSize(width = 1, height = 1)
        val invalidMine = PositiveNumber(value = 10)

        When(name = "넓이와 높이의 곱보다 마인 개수가 크면") {
            val exception = shouldThrow<IllegalArgumentException> {
                MinesweeperBoardGenerator.generate(
                    boardSize = invalidBoardSize,
                    mineCount = invalidMine,
                )
            }

            Then(name = "지뢰의 수는 보드의 영역보다 클 수 없다는 에러가 발생한다.") {
                val area = invalidBoardSize.area
                exception shouldHaveMessage "지뢰의 수는 ${area}보다 클 수 없습니다. 지뢰 수 : ${invalidMine.value}"
            }
        }

        val boardSize = BoardSize(width = 10, height = 10)
        val mine = PositiveNumber(value = 50)

        When(name = "넓이와 높이의 곱보다 마인 개수가 작으면") {
            val board = MinesweeperBoardGenerator.generate(
                boardSize = boardSize,
                mineCount = mine,
            )

            val blocks = board.sortedBlocks()

            Then(name = "지뢰찾기 객체가 생성된다.") {
                blocks shouldHaveSize boardSize.area
            }

            Then(name = "입력한 지뢰 개수만큼 개수가 생성된다.") {
                blocks.filter { it.hasMine } shouldHaveSize mine.value
            }
        }
    }

    Given(name = "2 X 2칸 보드가 주어지면") {
        val boardSize = BoardSize(width = 2, height = 2)

        Then(name = "지뢰 개수에 따라 노출 블록이 달라진다.") {
            forAll(
                row(3),
                row(2),
                row(1),
            ) { mineCount ->
                val mine = PositiveNumber(value = mineCount)

                val board = MinesweeperBoardGenerator.generate(
                    boardSize = boardSize,
                    mineCount = mine,
                )

                val blocks = board.sortedBlocks()
                    .filterNot { it.hasMine }

                blocks.forEach {
                    it.open().value shouldBe mineCount
                }
            }
        }
    }
})
