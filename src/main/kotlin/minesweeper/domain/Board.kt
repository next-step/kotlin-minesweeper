package minesweeper.domain

class Board(dimension: Dimension, private val mineFactory: MineFactory) {

    private val blocks: Array<Array<Block>> = Array(dimension) { _ -> Block.empty() }.also { blocks ->
        mineFactory.mines().forEach { mine ->
            blocks[mine.row - 1][mine.column - 1] = Mine
        }
    }

    fun block(coordinate: Coordinate): Block {
        require(blocks.size >= coordinate.row || blocks[0].size >= coordinate.column) {
            "board size 보다 작은 값을 입력해야 합니다. (1 ~ ${blocks.size}, 1 ~ ${blocks[0].size})"
        }

        return blocks[coordinate.row - 1][coordinate.column - 1]
    }

    fun allBlocks(): List<List<Block>> {
        return blocks.map { it.toList() }.toList()
    }

    companion object {
        private inline fun <reified T> Array(dimension: Dimension, init: (Coordinate) -> T): Array<Array<T>> {
            return Array(dimension.height.number) { x ->
                Array(dimension.width.number) { y ->
                    init.invoke(Coordinate(x + 1, y + 1))
                }
            }
        }
    }
}
