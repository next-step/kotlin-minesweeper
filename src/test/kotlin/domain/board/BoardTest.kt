package domain.board

import domain.location.Column
import domain.location.Location
import domain.location.Row
import domain.setting.Height
import domain.setting.Size
import domain.setting.Width
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {
    class BoardTest {

        @Test
        fun `지뢰의 행은 높이보다 작다`() {
            val height: Height = Height(4)
            val BoardValue: BoardValue = BoardValue.create(Size(height, Width(4)))
            val row: Row = Row(4)
            val mineLocations: Set<Location> = setOf(Location(row, Column(2)))
            assertThrows<IllegalArgumentException> { BoardValue.injectMines(mineLocations) }
        }

        @Test
        fun `지뢰의 열은 너비보다 작다`() {
            val width: Width = Width(4)
            val BoardValue: BoardValue = BoardValue.create(Size(Height(4), width))
            val column: Column = Column(4)
            val mineLocations: Set<Location> = setOf(Location(Row(2), column))
            assertThrows<IllegalArgumentException> { BoardValue.injectMines(mineLocations) }
        }
    }
}
