package domain.map

import domain.cell.Cell

@JvmInline
value class MapCapture(val cells: List<List<Cell>>)
