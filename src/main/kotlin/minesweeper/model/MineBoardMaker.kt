package minesweeper.model

const val START_POSITION = 0

const val MINE_ADD_VALUE = 1
const val MINE_EMPTY_VALUE = 0

class MineBoardMaker {
    private val counter: MineCounter = MineCounter()

    fun setMinePosition(board: Board, mineCount: Int): Board {
        val minePositions = (START_POSITION until board.getTotal()).shuffled()
                .take(mineCount)
                .sorted()

        for (position in START_POSITION until board.getTotal()) {
            turnInOrder(position, minePositions, board)
        }
        return board
    }

    fun setMineCount(board: Board): Board {
        for (position in START_POSITION until board.getTotal()) {

            val block = board.getBlock(position)

            if (block.type == Type.MINE) continue

            block.setCount(counter.getMineNumber(position, board))
        }
        return board
    }

    private fun turnInOrder(position: Int, minePositions: List<Int>, board: Board) {
        minePositions.forEach {
            if (it == position) {
                val block = board.getBlock(position)

                block.setMine()
            }
        }
    }
}
