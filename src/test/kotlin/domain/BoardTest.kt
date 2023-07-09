package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BoardTest{
    @Test
    internal fun `location으로 board를 찾을 수 있다`() {
        val board = Board(Location(1,0),Basic())
        val location = Location(1, 0)

        board.location shouldBe location
    }
}
