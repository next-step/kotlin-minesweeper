package mineswipper.domain.map

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SizeTest {

    @Test
    fun `Size는 넓이와 높이를 가진다`() {
        val width = 10
        val height = 10
        val size = Size(width, height)

        size.height shouldBe height
        size.width shouldBe width
    }
}