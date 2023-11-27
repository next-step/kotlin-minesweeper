package domain.builder

import domain.Coordinate
import domain.Row
import java.util.*

class RowBuilder {
    private var columns: SortedSet<Coordinate> = sortedSetOf()

    fun col(block: CoordinateBuilder.() -> Unit) {
        columns.addAll(CoordinateBuilder().apply(block).build())
    }

    fun build(): Row {
        return Row(columns)
    }
}

fun row(block: RowBuilder.() -> Unit): Row {
    return RowBuilder().apply(block).build()
}
