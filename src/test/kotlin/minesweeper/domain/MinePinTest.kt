package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MinePinTest {
    @Test
    fun `지뢰핀은 * 라는 문자를 리턴해준다`() {
        val mark = MinePin()

        mark.getMark() shouldBe "*"
    }
}
