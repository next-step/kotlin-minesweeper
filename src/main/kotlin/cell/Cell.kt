package cell

sealed interface Cell

data object BlankCell : Cell

data object MineCell : Cell
