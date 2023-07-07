package tdd.domain

import io.kotest.assertions.assertSoftly
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

        cell shouldBe Cell(Opened.of(aroundMineCount))
    }

    @Test
    fun isMine() {
        assertSoftly {
            Cell(Empty).isMine() shouldBe false
            Cell(Mine).isMine() shouldBe true
            Cell(Opened.of(3)).isMine() shouldBe false
        }
    }

    @Test
    fun isOpened() {
        assertSoftly {
            Cell(Empty).isOpened() shouldBe false
            Cell(Mine).isOpened() shouldBe false
            Cell(Opened.of(3)).isOpened() shouldBe true
        }
    }

    @Test
    fun isZero() {
        assertSoftly {
            Cell(Empty).isZero() shouldBe false
            Cell(Mine).isZero() shouldBe false
            Cell(Opened.of(3)).isZero() shouldBe false
            Cell(Opened.of(0)).isZero() shouldBe true
        }
    }

    @Test
    fun `Should return aroundMineCount`() {
        Cell(Opened.of(3)).aroundMineCount() shouldBe 3
    }
}
