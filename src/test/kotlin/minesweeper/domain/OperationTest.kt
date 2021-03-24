package minesweeper.domain

import minesweeper.domain.OperationTest.CellsBuilder.Companion.`⬜`
import minesweeper.domain.OperationTest.CellsBuilder.Companion.`💣`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OperationTest {
    @Test
    internal fun `열릴 위치를 입력받으면 셀이 열려있다`() {
        val cells = cells {
            row(`⬜`, `💣`, `⬜`)
        }.build()

        val operation = cells.operation()
        operation.open(Position(1, 1))

        assertThat(operation.result()).isEqualTo(Operation.Result.SUCCESS)
        assertThat(cells[0].open).isTrue()
    }

    @Test
    internal fun `열려 있는 곳을 다시 열면 이미 열려있음 응답을 받는다`() {
        val cells = cells {
            row(`⬜`, `💣`)
        }.build()

        val operation = cells.operation()
        operation.open(Position(1, 1))
        operation.open(Position(1, 1))

        assertThat(operation.result()).isEqualTo(Operation.Result.OPENED)
    }

    @Test
    internal fun `지뢰를 열면 폭파됨 응답을 받는다`() {
        val cells = cells {
            row(`⬜`, `💣`)
        }.build()

        val operation = cells.operation()
        operation.open(Position(2, 1))

        assertThat(operation.result()).isEqualTo(Operation.Result.EXPLOSION)
    }

    @Test
    internal fun `열었을 때 옆 셀이 비어있으면 같이 열린다`() {
        val cells = cells {
            row(`⬜`, `⬜`, `💣`)
        }.build()

        val operation = cells.operation()
        operation.open(Position(1, 1))

        assertThat(cells[0].open).isTrue()
        assertThat(cells[1].open).isTrue()
    }

    @Test
    fun `열었을 때 옆 셀이 여러개 있으면 같이 열린다`() {
        val cells = cells {
            row(`⬜`, `⬜`, `⬜`, `💣`)
        }.build()

        val operation = cells.operation()
        operation.open(Position(1, 1))

        assertThat(cells)
            .filteredOnAssertions { assertThat(it.open).isTrue() }
            .hasSize(3)

        assertThat(cells)
            .filteredOnAssertions { assertThat(it.open).isFalse() }
            .hasSize(1)
    }

    @Test
    internal fun `열었을 때 다른 줄도 닫혀있으면 열린다`() {
        val cells = cells {
            row(`⬜`, `⬜`, `⬜`, `⬜`, `💣`)
            row(`⬜`, `⬜`, `⬜`, `⬜`, `⬜`)
            row(`💣`, `⬜`, `⬜`, `⬜`, `⬜`)
        }.build()

        val operation = cells.operation()
        operation.open(Position(1, 1))

        assertThat(cells)
            .filteredOnAssertions { assertThat(it.open).isTrue() }
            .hasSize(13)
            .allSatisfy { assertThat(it.bomb).isFalse() }

        assertThat(cells)
            .filteredOnAssertions { assertThat(it.open).isFalse() }
            .hasSize(2)
            .allSatisfy { assertThat(it.bomb).isTrue() }
    }

    @Test
    fun `지뢰카운트가 1 이상이면 더 열리지 않는다`() {
        val cells = cells {
            row(`⬜`, `⬜`, `⬜`)
            row(`⬜`, `⬜`, `💣`)
        }.build()

        val operation = cells.operation()
        operation.open(Position(1, 1))

        assertThat(cells[1].open).isTrue()
        assertThat(cells[2].open).isFalse()
    }

    @Test
    fun `모두 열리면 종료응답을 받는다`() {
        val cells = cells {
            row(`⬜`, `💣`)
        }.build()
        val operation = cells.operation()
        operation.open(Position(1, 1))

        assertThat(operation.result()).isEqualTo(Operation.Result.END)
    }

    @Test
    fun `영역을 벗어난곳을 열면 OUT_OF_MATRIX 응답을 받는다`() {
        val cells = cells {
            row(`⬜`, `💣`)
        }.build()

        val operation = cells.operation()
        operation.open(Position(10, 10))

        assertThat(operation.result()).isEqualTo(Operation.Result.OUT_OF_MATRIX)
    }

    @Test
    internal fun `한방에 열 수 있다`() {
        val cells = cells {
            row(`⬜`, `💣`)
        }.build()

        cells.allOpen()

        assertThat(cells).allSatisfy {
            assertThat(it.open).isTrue()
        }
    }

    private fun cells(initializer: CellsBuilder.() -> Unit): CellsBuilder {
        return CellsBuilder().apply(initializer)
    }

    class CellsBuilder {
        private val rows: MutableList<IntArray> = mutableListOf()
        fun row(vararg number: Int) {
            rows.add(number.toList().toIntArray())
        }

        fun build(): Cells {
            val flatten = rows.flatMap { it.toList() }
            return MotherCells(
                rows[0].size, rows.size,
                CellSource.Default(
                    RandomDoubles(
                        flatten.map { it.toDouble() }
                    )
                )
            ).cells(flatten.count { it == `💣` })
        }

        companion object {
            const val `💣` = 0
            const val `⬜` = 1
        }
    }
}
