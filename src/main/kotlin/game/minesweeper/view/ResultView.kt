package game.minesweeper.view

import game.minesweeper.domain.map.Fragment
import game.minesweeper.domain.map.Row

object ResultView {
    fun drawMap(rows: List<Row>) {
        rows.forEach { row ->
            println(row.fragments().joinToString(separator = " ") { formatFragment(it) })
        }
    }

    private fun formatFragment(fragment: Fragment) = if (fragment.hasMine()) "X" else "□"
}
