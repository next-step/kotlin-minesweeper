package minesweeper.domain.cell

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class CellValueTest : FreeSpec({

    "좌표 값을 더할 수 있다." {
        CoordinateValue(1) + CoordinateValue(2) shouldBe CoordinateValue(3)
    }

    "좌표 값을 뺄 수 있다." {
        CoordinateValue(1) - CoordinateValue(1) shouldBe CoordinateValue(0)
    }
})
