package map

import map.tile.Tile

data class Row(
    val cols: List<Col>
)

data class Col(
    val tileType: Tile,
    val numOfNeighboringMine: Int
)
