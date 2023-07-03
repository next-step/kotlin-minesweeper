package fixture

import domain.Board
import domain.Row

fun board(
    vararg rows: Row,
) = Board(rows.toList())
