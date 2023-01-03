package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineCellTest : StringSpec({

    "MineCell copyWithOpen 함수 호출시 상태가 OPENED로 변경되고 오픈 결과 MINE_FOUND 이다." {
        //given
        val mineCell = MineCell(Position(1, 1))
        //when
        val copyWithOpen = mineCell.copyWithOpen()
        //then
        copyWithOpen.state shouldBe CellState.OPENED
        mineCell.openResult() shouldBe CellOpenResult.MINE_FOUND
    }
})
