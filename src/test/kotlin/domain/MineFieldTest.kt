package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineFieldTest : StringSpec({
    "올바른 크기와 지뢰 개수로 지뢰밭이 생성된다." {
        val height = Height(3)
        val width = Width(3)
        val mineCount = 3

        val mineField = MineField(height, width, mineCount)
        val state = mineField.getState()

        state.cells.size shouldBe 3
        state.cells[0].size shouldBe 3
    }

    "지뢰 개수가 셀의 총 개수를 초과하면 예외가 발생한다." {
        val height = Height(3)
        val width = Width(3)
        val invalidMineCount = 10

        shouldThrow<IllegalArgumentException> {
            MineField(height, width, invalidMineCount)
        }
    }
})
