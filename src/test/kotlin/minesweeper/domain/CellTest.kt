package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `Cell은 지뢰와 빈 cell 클래스로 이루어져있다`() {
        val mineCell = MineCell
        mineCell.shouldBeInstanceOf<MineCell>()

        val emptyCell = EmptyCell
        emptyCell.shouldBeInstanceOf<EmptyCell>()
    }
}