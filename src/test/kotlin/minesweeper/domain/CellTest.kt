package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `Cell은 CellType과 주변 지뢰 개수를 갖는 클래스이다`() {
        val cell = Cell(CellType.MINE)

        cell.type shouldBe CellType.MINE
        cell.aroundMineCount shouldBe 0
    }
}