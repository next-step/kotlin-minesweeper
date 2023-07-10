package minesweeper2.domain

private fun Int.toHeight() = Length.of(this.toString())
private fun Int.toWidth() = Length.of(this.toString())
private fun Int.toCount() = MineCount.of(this.toString())
