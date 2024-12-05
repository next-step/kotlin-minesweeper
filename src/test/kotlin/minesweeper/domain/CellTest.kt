package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `지뢰가 없는 Cell을 생성한다`() {
        val cell = Cell(isMine = false)
        cell.isMine shouldBe false
    }

    @Test
    fun `지뢰가 있는 Cell을 생성한다`() {
        val cell = Cell(isMine = true)
        cell.isMine shouldBe true
    }
}
