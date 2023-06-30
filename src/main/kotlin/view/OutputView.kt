package view

import map.Tile

interface OutputView {
    fun drawMineMap(mineMap: List<List<String>>)
}
