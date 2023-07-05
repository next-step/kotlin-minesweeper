package view

import map.Col
import map.Row
import map.tile.MineTile
import map.tile.PlainTile

class StdoutOutputView : OutputView {
    override fun drawMineMap(rows: List<Row>) {
        println("지뢰찾기 게임 시작")
        rows.forEach {
            printTileColumns(it.cols)
        }
    }

    private fun printTileColumns(cols: List<Col>) {
        cols.forEach {
            print("${characterizeTile(it)} ")
        }
        println()
    }
    private fun characterizeTile(col: Col): String {
        return when (col.tileType) {
            is PlainTile -> col.numOfNeighboringMine.toString()
            is MineTile -> "*"
        }
    }
}
