package domain.cell

sealed class Cell {

    object Mine : Cell()

    data class Ground(
        val aroundMineCount: AroundMineCount,
    ) : Cell()
}
