package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class CellValueTest : FreeSpec({

    "좌표값이 음수일 경우 예외가 발생한다." {
        val exception = shouldThrowExactly<IllegalArgumentException> { CoordinateValue(-1) }
        exception.message shouldBe "셀 좌표 값은 음수일 수 없습니다."
    }

    "좌표 값을 더할 수 있다." {
        CoordinateValue(1) + CoordinateValue(2) shouldBe CoordinateValue(3)
    }

    "좌표 값을 뺄 수 있다." {
        CoordinateValue(1) - CoordinateValue(1) shouldBe CoordinateValue(0)
    }
})
