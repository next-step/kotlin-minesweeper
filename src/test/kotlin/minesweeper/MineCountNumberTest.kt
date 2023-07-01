package minesweeper

import domain.MineCountNumber
import domain.PositiveNumber
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class MineCountNumberTest {
    @Test
    fun `지뢰찾기 영역 내 가능한 수 만큼 지뢰개수를 가질 수 있다`() {
        val height = PositiveNumber(10)
        val width = PositiveNumber(10)
        val injectValue = PositiveNumber(100)

        val mineCountNumber = MineCountNumber(injectValue, height, width)

        mineCountNumber.value shouldBe injectValue.value
    }

    @Test
    fun `지뢰찾기 영역 내 가능한 수를 초과하면 에러를 반환한다`() {
        val height = PositiveNumber(10)
        val width = PositiveNumber(10)
        val injectValue = PositiveNumber(101)

        assertThrows<IllegalArgumentException>(ErrorCode.INVALID_MINE_COUNT_NUMBER_ERROR.msg) {
            MineCountNumber(injectValue, height, width)
        }
    }
}
