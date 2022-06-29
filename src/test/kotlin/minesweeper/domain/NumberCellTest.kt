package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.enums.CellStatus

class NumberCellTest : StringSpec({
    "NumberCell 의 초기 상태값은 CLOSE" {
        val numberCell = NumberCell()
        numberCell.status shouldBe CellStatus.CLOSE
    }
})
