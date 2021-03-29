package minesweeper.domain

interface CellFactory {
    fun cells(bomb: Int, matrix: Matrix): List<Cell>

    class Default(private val randomDoubles: RandomDoubles) : CellFactory {
        constructor(size: Int) : this(RandomDoubles(size))

        override fun cells(bomb: Int, matrix: Matrix): List<Cell> {
            val protoCells = protoCells(bomb)

            protoCells.updateSide(matrix)

            protoCells.increaseCount()

            val cells = protoCells.cell()

            val link = Link(matrix, cells)
            cells.forEachIndexed { index, cell ->
                cell.link = link.cells(index)
            }
            return cells
        }

        private fun protoCells(bomb: Int): ProtoCells {
            val boundary = boundary(bomb)
            return ProtoCells(randomDoubles.map { ProtoCell(it <= boundary) })
        }

        private fun boundary(count: Int) = randomDoubles.sorted().take(count).last()
    }
}
