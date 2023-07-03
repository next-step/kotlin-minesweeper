package fixture

import domain.Board
import domain.Row

fun mineBoard(
    vararg rows: Row,
) = Board(rows.toList())
