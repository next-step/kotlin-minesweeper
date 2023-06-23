package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.throwable.shouldHaveMessage

class MinesweeperBoardGeneratorTest : BehaviorSpec({

    Given(name = "넓이와 높이, 마인 개수가 주어지면") {
        val invalidWidth = BoardSize(value = 1)
        val invalidHeight = BoardSize(value = 1)
        val invalidMine = PositiveNumber(value = 10)

        When(name = "넓이와 높이의 곱보다 마인 개수가 크면") {
            val exception = shouldThrow<IllegalArgumentException> {
                MinesweeperBoardGenerator.generate(
                    width = invalidWidth,
                    height = invalidHeight,
                    mineCount = invalidMine,
                )
            }

            Then(name = "지뢰의 수는 보드의 영역보다 클 수 없다는 에러가 발생한다.") {
                val area = invalidHeight * invalidWidth
                exception shouldHaveMessage "지뢰의 수는 ${area.value}보다 클 수 없습니다. 지뢰 수 : ${invalidMine.value}"
            }
        }

        val width = BoardSize(value = 10)
        val height = BoardSize(value = 10)
        val mine = PositiveNumber(value = 50)

        When(name = "넓이와 높이의 곱보다 마인 개수가 작으면") {
            val board = MinesweeperBoardGenerator.generate(
                width = width,
                height = height,
                mineCount = mine,
            )

            Then(name = "지뢰찾기 객체가 생성된다.") {
                board.sortedBlocks() shouldHaveSize (width * height).value
            }
        }
    }

})
