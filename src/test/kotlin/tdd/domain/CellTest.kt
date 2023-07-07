package tdd.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
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

    @Test
    fun `When cell created, state is Closed`() {
        Cell().state shouldBe instanceOf<Closed>()
    }
}
