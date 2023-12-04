package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    fun `Cell은 지뢰와 빈 cell 클래스로 이루어져있다`() {
        val mineCell = MineCell()
        mineCell.shouldBeInstanceOf<MineCell>()

        val emptyCell = EmptyCell()
        emptyCell.shouldBeInstanceOf<EmptyCell>()
    }

    @Test
    fun `MineCell은 열 수 없다`() {
        val mineCell = MineCell()

        mineCell.isOpened shouldBe false
        shouldThrow<IllegalAccessException> {
            mineCell.open()
        }
    }

    @Test
    fun `EmptyCell은 자신이 현재 열린 상태인지 알 수 있으며, 초기 상태는 false이다`() {
        val emptyCell = EmptyCell()
        emptyCell.isOpened shouldBe false
    }

    @Test
    fun `EmptyCell은 open 메서드로 자신을 열 수 있다`() {
        val emptyCell = EmptyCell()
        emptyCell.isOpened shouldBe false

        emptyCell.open()

        emptyCell.isOpened shouldBe true
    }
}