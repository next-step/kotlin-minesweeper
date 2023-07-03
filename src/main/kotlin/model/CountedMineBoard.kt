package model

import model.minemark.Safety

data class CountedMineBoard(val mineBoard: MineBoard) {

    init {
        require(mineBoard.doesNotContainsMark(NOT_ALLOWED_MARK)) {
            "mineBoard must not contain $NOT_ALLOWED_MARK mark. but provided `$mineBoard`"
        }
    }

    companion object {
        private val NOT_ALLOWED_MARK = Safety()
    }
}
