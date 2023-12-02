package minesweeper.domain

import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RowTest {
    @Test
    fun `한 줄을 구성하는 Cell은 모두 같은 y값을 가지면 정상적으로 생성된다 `() {
        val cell1 = Cell(Point(1, 2), State.MINE)
        val cell2 = Cell(Point(2, 2), State.MINE)
        val cell3 = Cell(Point(3, 2), State.MINE)
        Row(sortedSetOf(cell1, cell2, cell3)) shouldNotBe null
    }

    @Test
    fun `한 줄을 구성하는 Cell이 다른 y값을 가질 경우 IllegalArgumentException 발생한다`() {
        val cell1 = Cell(Point(1, 2), State.MINE)
        val cell2 = Cell(Point(2, 2), State.MINE)
        val cell3 = Cell(Point(2, 3), State.MINE)
        assertThrows<IllegalArgumentException> { Row(sortedSetOf(cell1, cell2, cell3)) }
    }
}
