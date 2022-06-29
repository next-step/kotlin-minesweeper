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

    "NumberCell Open 상태에 따라 출력을 다르게 한다." {
        val numberCell = NumberCell()
        numberCell.text() shouldBe "C"
        numberCell.open()
        numberCell.text() shouldBe "0"
    }
})
