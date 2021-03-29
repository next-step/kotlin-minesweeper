package minesweeper.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {
    @Test
    internal fun `높이와 너비가 0보다 크다`() {
        assertThrows<IllegalArgumentException> {
            Board(
                cells {
                    blank() and bomb()
                },
                3
            )
        }
    }

    @Test
    internal fun `지뢰가 존재해야 한다`() {
        assertThrows<IllegalArgumentException> {
            Board(
                cells {
                    blank() and blank()
                },
                2
            )
        }
    }

    @Test
    internal fun `지뢰가 아닌 셀이 존재해야 한다`() {
        assertThrows<IllegalArgumentException> {
            Board(
                cells {
                    bomb() and bomb()
                },
                2
            )
        }
    }

    private fun cells(initializer: CellsBuilder.() -> Unit): List<Cell> {
        return CellsBuilder().apply(initializer).build()
    }

    class CellsBuilder {
        lateinit var cells: List<Cell>
        fun build(): List<Cell> {
            return cells
        }

        fun blank(): List<Cell> {
            return listOf(Cell(CellState.BlankCell()))
        }

        fun bomb(): List<Cell> {
            return listOf(Cell(CellState.BombCell()))
        }

        infix fun List<Cell>.and(other: List<Cell>) {
            cells = this + other
        }
    }
}
