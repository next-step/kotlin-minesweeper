package fixture

import domain.MineBoard
import domain.Row

fun mineBoard(
    vararg rows: Row,
) = MineBoard(rows.toList())
