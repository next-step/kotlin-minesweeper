package minesweeper.domain.flag

class MineFlag : Flag(name = MINE_NAME) {

    companion object {
        private const val MINE_NAME = "*"
    }
}
