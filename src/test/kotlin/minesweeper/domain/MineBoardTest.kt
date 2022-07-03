package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Land
import minesweeper.domain.cell.Mine

internal class MineBoardTest : FreeSpec({

    "지뢰판의 셀이 비어있을 경우 예외가 발생한다." {
        val exception =
            shouldThrowExactly<IllegalArgumentException> { MineBoard(emptyList()) }
        exception.message shouldBe "지뢰판은 빌 수 없습니다."
    }

    "높이, 너비, 지뢰 개수를 입력 받아 지뢰판을 만들 수 있다." {
        val mineBoard = MineBoard.createWithRandomStrategy(
            height = 5,
            width = 5,
            mineCount = 5
        )

        mineBoard.cells.shouldHaveSize(25)
        mineBoard.cells.count { it.dot == Mine } shouldBe 5
        mineBoard.cells.count { it.dot == Land } shouldBe 20
    }

    "높이, 너비, 지뢰 개수가 올바르지 않으면 예외가 발생한다." - {
        "높이가 0 이하인 경우 예외가 발생한다." {
            shouldThrowExactly<IllegalArgumentException> {
                MineBoard.createWithRandomStrategy(
                    height = 0,
                    width = 1,
                    mineCount = 0
                )
            }
        }

        "너비가 0 이하인 경우 예외가 발생한다." {
            shouldThrowExactly<IllegalArgumentException> {
                MineBoard.createWithRandomStrategy(
                    height = 1,
                    width = 0,
                    mineCount = 0
                )
            }
        }

        "지뢰 개수가 0 미만인 경우 예외가 발생한다." {
            shouldThrowExactly<IllegalArgumentException> {
                MineBoard.createWithRandomStrategy(
                    height = 1,
                    width = 1,
                    mineCount = -1
                )
            }
        }
    }
})
