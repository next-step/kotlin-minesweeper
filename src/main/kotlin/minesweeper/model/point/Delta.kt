package minesweeper.model.point

data class Delta(
    val verticalDelta: Int,
    val horizontalDelta: Int,
) {
    companion object {
        val deltas: List<Delta> = listOf(
            1 to 1,
            1 to 0,
            1 to -1,
            0 to 1,
            0 to -1,
            -1 to 1,
            -1 to 0,
            -1 to -1,
        ).map { Delta(it.first, it.second) }
    }
}
