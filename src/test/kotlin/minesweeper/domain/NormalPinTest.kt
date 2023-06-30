package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class NormalPinTest {
    @Test
    fun `일반 핀은 C 라는 문자를 리턴해준다`() {
        val mark = NormalPin()

        mark.getMark() shouldBe "C"
    }
}
