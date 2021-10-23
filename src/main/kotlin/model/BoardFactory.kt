package model

class BoardFactory(private val mineStrategy: MineStrategy) {
    fun build(height: Int, width: Int, mineCount: Int): Board {
        val isMine: Iterator<Boolean> = mineStrategy.isMineIterator(width * height, mineCount)

        return Board(
            IntRange(0, height - 1).map { heightIndex ->
                Row(
                    IntRange(0, width - 1).map { widthIndex ->
                        Cell(
                            if (isMine.next()) CellType.Mine else CellType.Plain,
                            Position(widthIndex, heightIndex, width - 1, height - 1)
                        )
                    }
                )
            }
        )
    }
}