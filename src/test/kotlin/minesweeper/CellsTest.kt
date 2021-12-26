package minesweeper

import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.Height
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.Width
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellsTest {

    @Test
    fun `Postion, Cell로 구성된 Map을 가지고 Cells를 생성할 수 있다`() {
        val positions = Positions.of(Width.from(7), Height.Companion.from(7))

        val map: Map<Position, Cell> = positions.positions.associateWith { Cell.SafetyCell }

        assertThat(Cells.from(map).cells).isEqualTo(map)
    }
}
