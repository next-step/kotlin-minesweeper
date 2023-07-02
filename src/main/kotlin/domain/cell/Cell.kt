package domain.cell

sealed class Cell {

    object Mine : Cell()
    object Ground : Cell()
}
