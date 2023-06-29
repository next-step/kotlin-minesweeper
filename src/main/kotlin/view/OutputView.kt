package view

import map.MapElement

interface OutputView {
    fun drawMineMap(mineMap: List<List<String>>)
}
