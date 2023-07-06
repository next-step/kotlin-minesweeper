package tdd.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `Create an empty cell`() {
        Cell().state shouldBe Empty
    }

    @Test
    fun `Create a cell that has a mine`() {
        Cell(Mine).state shouldBe Mine
    }
}
