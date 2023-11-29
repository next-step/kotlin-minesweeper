package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `지뢰가 맞으면 true`() {
        // given
        val cell = Cell(CellStatus.MINE)

        // when
        val result = cell.isMine()

        // then
        result shouldBe true
    }

    @Test
    fun `지뢰가 아나면 false`() {
        // given
        val cell = Cell(CellStatus.EMPTY)

        // when
        val result = cell.isMine()

        // then
        result shouldBe false
    }

    @Test
    fun `현재 cell이 열려 있으면 true`() {
        // given
        val cell = Cell(CellStatus.EMPTY)

        // when
        val result = cell.isOpen()

        // then
        result shouldBe false
    }

    @Test
    fun `현재 cell이 열려 있지 않으면 false`() {
        // given
        val cell = Cell(CellStatus.EMPTY)
        cell.open()

        // when
        val result = cell.isOpen()

        // then
        result shouldBe true
    }
}
