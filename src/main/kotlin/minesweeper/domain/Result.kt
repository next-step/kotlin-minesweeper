package minesweeper.domain

enum class Result {
    OPENED,
    SUCCESS,
    OUT_OF_MATRIX,
    EXPLOSION {
        override fun end() = true
    },
    END {
        override fun end() = true
    };

    open fun end() = false
}
