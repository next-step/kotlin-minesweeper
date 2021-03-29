package minesweeper.domain

fun board(initializer: BoardBuilder.() -> Unit): BoardBuilder {
    return BoardBuilder().apply(initializer)
}

class BoardBuilder {
    private val rows: MutableList<IntArray> = mutableListOf()
    fun row(vararg number: Int) {
        rows.add(number.toList().toIntArray())
    }

    fun build(): Board {
        val flatten = rows.flatMap { it.toList() }
        return BoardFactory(
            rows[0].size, rows.size,
            cellFactory()
        ).board(flatten.count { it == `💣` })
    }

    fun cellFactory(): CellFactory {
        val flatten = rows.flatMap { it.toList() }
        return CellFactory.Default(
            RandomDoubles(
                flatten.map { it.toDouble() }
            )
        )
    }

    companion object {
        const val `💣` = 0
        const val `⬜` = 1
    }
}
