package domain

class MineBoard(private val mineBoard: Map<Position, Block>) {

    companion object {
        fun getInstance(mineBoard: Map<Position, Block>): MineBoard {
            return MineBoard(mineBoard)
        }
    }
}
