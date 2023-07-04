package domain.cell

sealed class Cell {

    abstract val openState: OpenState

    data class Mine(
        override val openState: OpenState,
    ) : Cell()

    data class Ground(
        override val openState: OpenState,
        val aroundMineCount: AroundMineCount,
    ) : Cell()

    companion object {

        fun hideMine(): Cell {
            return Mine(
                openState = OpenState.HIDE,
            )
        }

        fun hideGround(
            aroundMineCount: AroundMineCount,
        ): Cell {
            return Ground(
                openState = OpenState.HIDE,
                aroundMineCount = aroundMineCount,
            )
        }
    }
}
