package minesweeper.domain

interface CellFactory {
    fun cells(bomb: Int, matrix: Matrix): List<Cell>

    class Default(private val randomDoubles: RandomDoubles) : CellFactory {
        constructor(size: Int) : this(RandomDoubles(size))

        override fun cells(bomb: Int, matrix: Matrix): List<Cell> {
            return protoCells(bomb, matrix).cells()
        }

        private fun protoCells(bomb: Int, matrix: Matrix): ProtoCells {
            val boundary = boundary(bomb)
            return ProtoCells(randomDoubles.map { ProtoCell(it <= boundary) }, matrix)
        }

        private fun boundary(count: Int) = randomDoubles.sorted().take(count).last()
    }
}
