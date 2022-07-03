package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.CoordinateValue

internal class CellValueTest : FreeSpec({

    "좌표값이 음수일 경우 예외가 발생한다." {
        val exception = shouldThrowExactly<IllegalArgumentException> { CoordinateValue(-1) }
        exception.message shouldBe "셀 좌표 값은 음수일 수 없습니다."
    }
})
