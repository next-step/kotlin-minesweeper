package minesweeper.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class CellValueTest : FreeSpec({

    "덧셈 연산자를 이용해 좌표 값을 더할 수 있다." {
        CoordinateValue(1) + CoordinateValue(2) shouldBe CoordinateValue(3)
    }

    "뺄셈 연산자를 이용해 좌표 값을 뺄 수 있다." {
        CoordinateValue(1) - CoordinateValue(1) shouldBe CoordinateValue(0)
    }
})
