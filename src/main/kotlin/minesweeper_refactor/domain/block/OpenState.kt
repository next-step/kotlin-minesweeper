package minesweeper_refactor.domain.block

enum class OpenState {
    MINE,
    DO_NOTHING,
    CHAIN_OPEN,
    ;

    companion object {

        fun valueOf(blockState: BlockState): OpenState = when (blockState) {
            BlockState.MINE -> MINE
            BlockState.ZERO -> CHAIN_OPEN
            else -> DO_NOTHING
        }
    }
}
