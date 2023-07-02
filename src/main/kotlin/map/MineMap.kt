package map

import map.position.Position
import map.position.selector.MinePositionSelector
import map.tile.MineTile
import map.tile.PlaneTile
import map.tile.Tile
import model.Height
import model.MineCount
import model.Width

class MineMap(
    private val property: Property,
    private val minePositionSelector: MinePositionSelector
) {

    private val mineMap: MutableMap<Position, Tile?> = mutableMapOf()

    init {
        initMineMap()
        fillMineTiles()
        fillPlainTilesForEmptyPosition()
    }

    fun getMapAsSymbol(): List<List<String>> {
        return (0 until height()).map { y ->
            (0 until width()).map { x ->
                mineMap[Position(y, x)]!!.getSymbol()
            }
        }
    }

    private fun initMineMap() {
        (0 until height()).map { y ->
            (0 until width()).map { x ->
                mineMap[Position(y, x)] = null
            }
        }
    }

    private fun fillMineTiles() {
        (0 until mineCount()).map {
            // 중복 여부에 따라서 반복이랑 분기를 해야하는걸 보니
            // MinePositionSelector 로 책임을 분리하면 안되는것같기도하다
            while (true) {
                val minePosition = minePositionSelector.select()
                if (mineMap[minePosition] != null) {
                    continue
                }
                mineMap[minePosition] = MineTile()
                break
            }
        }
    }

    private fun fillPlainTilesForEmptyPosition() {
        val emptyPositions = mineMap.filter { it.value == null }
        emptyPositions.forEach { (pos, _) ->
            mineMap[pos] = PlaneTile()
        }
    }

    // 특정 위치의 요소를 MineMap 클래스에서 바꾸는 책임을 갖는게 적절한지 조금 애매하다
    private fun height() = property.height.value
    private fun width() = property.width.value
    private fun mineCount() = property.mineCount.value

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
