package game.minesweeper.view

import game.minesweeper.domain.map.Fragment
import game.minesweeper.domain.map.MineMap

object ResultView {
    fun drawMap(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        mineMap.rows.forEach { row ->
            println(row.fragments.joinToString(separator = " ") { formatFragment(it) })
        }
    }

    private fun formatFragment(fragment: Fragment) = if (fragment.hasMine()) "*" else fragment.borderMine().toString()
}
