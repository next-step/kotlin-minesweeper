package minesweeper.model.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeBetween

class RowsTest : StringSpec({
    "열의 길이 범위 내의 랜덤한 위치값을 반환받는다" {
        // given
        val size = 10
        val row = Rows(size)

        // when
        val result = row.getPosition()

        // then
        result.shouldBeBetween(0, size)
    }
})
