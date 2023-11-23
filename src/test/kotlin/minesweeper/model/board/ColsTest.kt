package minesweeper.model.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeBetween

class ColsTest : StringSpec({
    "행의 길이 범위 내의 랜덤한 위치값을 반환받는다" {
        // given
        val size = 10
        val col = Cols(size)

        // when
        val result = col.getPosition()

        // then
        result.shouldBeBetween(0, size)
    }
})
