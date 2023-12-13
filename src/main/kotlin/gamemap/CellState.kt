package gamemap

sealed interface CellState {
    fun open(): CellState
    object Open : CellState {
        override fun open(): CellState {
            check(this != Open) { "cannot open a open cell $this" }
            return Open
        }
    }
    object Close : CellState {
        override fun open(): CellState {
            return Open
        }
    }
}
