package view

import map.Row

interface OutputView {
    fun drawMineMap(rows: List<Row>)
}
