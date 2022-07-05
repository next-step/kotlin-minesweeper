package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Land
import minesweeper.domain.cell.Mine
import minesweeper.MineBoard

internal class MineBoardTest : FreeSpec({

    "지뢰판의 셀이 비어있을 경우 예외가 발생한다." {
        val exception =
            shouldThrowExactly<IllegalArgumentException> { MineBoard(emptyList()) }
        exception.message shouldBe "지뢰판은 빌 수 없습니다."
    }

    "높이, 너비, 지뢰 개수를 입력 받아 지뢰판을 만들 수 있다." {
        val mineBoard = MineBoard.create(
            height = 5,
            width = 5,
            mineCount = 5
        )

        mineBoard.cells.shouldHaveSize(25)
        mineBoard.cells.count { it.dot == Mine } shouldBe 5
        mineBoard.cells.count { it.dot == Land } shouldBe 20
    }
})
