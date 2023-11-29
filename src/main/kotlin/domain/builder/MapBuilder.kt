package domain.builder

import domain.*
import domain.Map
import java.util.*

class MapBuilder {
    private var rows: SortedSet<Row> = sortedSetOf()

    fun row(block: RowBuilder.() -> Unit) {
        rows.add(RowBuilder().apply(block).build())
    }

    fun mines(block: () -> Coordinates) {
        val mines = block()
        rows = rows.map {
            it.createSafeOrMine(mines)
        }.toSortedSet()
    }

    fun build(): Map {
        return Map(rows)
    }
}

fun map(block: MapBuilder.() -> Unit): Map {
    return MapBuilder().apply(block).build()
}
