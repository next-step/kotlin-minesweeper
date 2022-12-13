package minesweeper.domain

import io.kotest.matchers.shouldBe
import minesweeper.domain.Mine
import minesweeper.domain.Position
import org.junit.jupiter.api.Test


internal class MineTest {
    @Test
    fun `지뢰는 위치를 가진다`() {
        val mine = Mine(Position(10, 10))

        mine.position.width shouldBe 10
        mine.position.height shouldBe 10
    }
}