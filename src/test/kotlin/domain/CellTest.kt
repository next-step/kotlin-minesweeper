package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CellTest : StringSpec({
    "지뢰가 아닌 셀은 Empty 상태이다." {
        val cell = Cell.create(false)
        cell shouldBe Cell.Empty
    }

    "지뢰가 있는 셀은 MineCell 상태이다." {
        val cell = Cell.create(true)
        cell shouldBe Cell.MineCell
    }
})
