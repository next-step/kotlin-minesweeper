package game.minesweeper.view

import game.minesweeper.domain.map.Fragment
import game.minesweeper.domain.map.MineMap

object ResultView {
    fun drawMap(mineMap: MineMap) {
        mineMap.rows().forEach { row ->
            println(row.fragments().joinToString(separator = " ") { formatFragment(it) })
        }
    }

    private fun formatFragment(fragment: Fragment) = if (fragment.hasMine()) "X" else "□"
}
