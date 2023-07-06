package map

import map.position.Position
import map.position.selector.MinePositionSelector
import map.tile.MineTile
import map.tile.PlainTile
import map.tile.Tile
import model.Height
import model.MineCount
import model.Width

class MineMap(
    property: Property,
    private val minePositionSelector: MinePositionSelector
) {
    private var mineMap: MutableMap<Position, Tile?> = mutableMapOf()
    private val height = property.height.value
    private val width = property.width.value
    private val mineCount = property.mineCount.value
    private var currMineCount = 0

    init {
        initMap()
        generateMinePositions().forEach(::plantMine)
    }

    fun getMapSnapShot(): List<Row> {
        return (0 until height).map { y ->
            Row(
                (0 until width).map { x ->
                    val pos = Position(y, x)
                    Col(mineMap[pos]!!, countNeighboringMine(pos))
                }
            )
        }
    }

    private fun plantMine(pos: Position) {
        validatePlantingMineAvailable()
        mineMap[pos] = MineTile
        currMineCount++
    }

    private fun countNeighboringMine(pos: Position): Int {
        val neighbors = pos.getAdjacentNeighbors()

        var mineCount = 0
        neighbors.forEach {
            val tile = mineMap[it]
            if (tile != null && tile is MineTile) {
                mineCount++
            }
        }
        return mineCount
    }
    private fun validatePlantingMineAvailable() {
        if (currMineCount == (height * width)) {
            throw IllegalStateException("더 이상 지뢰를 매설할 공간이 없습니다")
        }
    }

    private fun initMap() {
        (0 until height).map { y ->
            (0 until width).map { x ->
                mineMap[Position(y, x)] = PlainTile
            }
        }
    }

    private fun generateMinePositions(): List<Position> {
        val minePositions = mutableSetOf<Position>()

        (0 until mineCount).forEach { _ ->
            addMinePosition(minePositions)
        }
        return minePositions.toList()
    }

    private fun addMinePosition(minePositions: MutableSet<Position>) {
        while (true) {
            val minePos = minePositionSelector.select()
            if (!minePositions.contains(minePos)) {
                minePositions.add(minePos)
                break
            }
        }
    }

    data class Property(
        val height: Height,
        val width: Width,
        val mineCount: MineCount,
    ) {
        init {
            require(height.value * width.value >= mineCount.value) { "지뢰의 갯수가 전체 타일의 갯수보다 크거나 같을수없습니다." }
        }
    }
}
