package view

import domain.Coordinate
import domain.Map
import domain.Mine
import domain.Row

private const val MINE_SYMBOL = "*"
private const val SAFE_ZONE_SYMBOL = "C"
private const val COLUMN_DELIMITER = " "
private const val ROW_DELIMITER = "\n"

fun Coordinate.view(): String {
    if (this is Mine) {
        return MINE_SYMBOL
    }
    return SAFE_ZONE_SYMBOL
}

fun Row.view(): String {
    return columns.joinToString(separator = COLUMN_DELIMITER) { it.view() }
}


fun Map.view(): String {
    return rows.joinToString(separator = ROW_DELIMITER) { it.view() }
}
