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

    private fun plantMine(pos: Position) {
        validatePlantingMineAvailable()
        mineMap[pos] = MineTile()
        currMineCount++
    }

    fun getMapAsSymbol(): List<List<String>> {
        return (0 until height).map { y ->
            (0 until width).map { x ->
                mineMap[Position(y, x)]!!.getSymbol()
            }
        }
    }

    private fun validatePlantingMineAvailable() {
        if (mineCount == (height * width)) {
            throw IllegalStateException("더 이상 지뢰를 매설할 공간이 없습니다")
        }
    }

    private fun initMap() {
        (0 until height).map { y ->
            (0 until width).map { x ->
                mineMap[Position(y, x)] = PlainTile()
            }
        }
    }

    private fun generateMinePositions(): List<Position> {
        val minePositions = mutableSetOf<Position>()

        (0 until mineCount).forEach { _ ->
            while (true) {
                val minePos = minePositionSelector.select()
                if (!minePositions.contains(minePos)) {
                    minePositions.add(minePos)
                    break
                }
            }
        }
        return minePositions.toList()
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
