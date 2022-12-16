package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


internal class PositionTest {
    @Test
    fun `위치 정보는 높이와 너비정보를 가진다`() {
        val position = Position(10, 10)

        position.width shouldBe 10
        position.height shouldBe 10
    }
}
