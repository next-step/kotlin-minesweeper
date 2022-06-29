package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.enums.CellStatus

class NumberCellTest : StringSpec({
    "NumberCell 의 초기 상태값은 CLOSE" {
        val numberCell = NumberCell()
        numberCell.status shouldBe CellStatus.CLOSE
    }

    "NumberCell 을 open 하면 상태가 OPEN으로 변경된다" {
        val numberCell = NumberCell()
        numberCell.open()
        numberCell.status shouldBe CellStatus.OPEN
    }
})
