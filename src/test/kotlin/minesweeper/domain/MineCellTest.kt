package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineCellTest : StringSpec({
    "MineCell Open 시 false를 반환한다" {
        val mineCell = MineCell
        mineCell.open() shouldBe false
    }
})
