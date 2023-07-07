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

    @Test
    fun `When cell opened, cell has the sum of around mine counts`() {
        val aroundMineCount = 3
        val cell = Cell()
        cell.open(aroundMineCount)

        cell shouldBe Cell(Opened(aroundMineCount))
    }

    @Test
    fun isMine() {
        Cell(Empty).isMine() shouldBe false
        Cell(Mine).isMine() shouldBe true
        Cell(Opened(3)).isMine() shouldBe false
    }
}
