package minesweeper

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


internal class MineTest {
    @Test
    fun `지뢰는 위치를 가진다`() {
        val mine = Mine(10, 10)
        mine.width shouldBe 10
        mine.height shouldBe 10
    }
}