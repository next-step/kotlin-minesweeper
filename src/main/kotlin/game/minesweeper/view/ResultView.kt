package game.minesweeper.view

import game.minesweeper.domain.map.Fragment
import game.minesweeper.domain.map.MineMap

object ResultView {
    fun drawMap(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        mineMap.fragments.chunked(mineMap.width()).forEach {
            println(it.joinToString(separator = " ") { fragment -> formatFragment(fragment) })
        }
    }

    private fun formatFragment(fragment: Fragment) = if (fragment.hasMine()) "*" else fragment.borderMine().toString()
}
