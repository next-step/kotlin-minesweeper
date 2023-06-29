package map

import map.position.Position
import map.position.selector.MinePositionSelector
import model.Height
import model.MineCount
import model.Width

class MineMap(
    private val property: Property,
    private val minePositionSelector: MinePositionSelector
) {

    private val mapList: List<List<MapElement>> = draw()

    // 이렇게 값을 제공해주는것이 조금 부자연스러운것같다 (List<List<MapElement>> 가 더나을까?)
    // 사실 마음같아서는 List<List<T>> 이런 값을 MineMap 외부가 모르게하고싶다.
    fun getMapAsSymbol(): List<List<String>> {
        return mapList.map { row -> row.map { it.getSymbol() } }
    }

    private fun draw(): List<List<MapElement>> {
        val defaultType = if(isFullOfMine())  MapElementType.MINE else MapElementType.EMPTY
        val map = (0 until height()).map { y ->
            (0 until width()).map { x ->
                MapElement(Position(y, x), defaultType)
            }
        }
        if (isFullOfMine()) {
            return map
        }
        fillMineTiles(map)
        return map
    }

    private fun fillMineTiles(map: List<List<MapElement>>) {
        (0 until mineCount()).map {
            // 중복 여부에 따라서 반복이랑 분기를 해야하는걸 보니
            // MinePositionSelector 로 책임을 분리하면 안되는것같기도하다
            while (true) {
                val minePosition = minePositionSelector.select()
                val tile = map[minePosition.y][minePosition.x]
                if (isEmptyTile(tile)) {
                    changeTypeToMine(tile)
                    break
                }
            }
        }
    }

    // 특정 위치의 요소를 MineMap 클래스에서 바꾸는 책임을 갖는게 적절한지 조금 애매하다
    private fun changeTypeToMine(mapElement: MapElement) {
        mapElement.changeType(MapElementType.MINE)
    }
    private fun isEmptyTile(mapElement: MapElement) = !mapElement.isMine()

    private fun isFullOfMine() = (height() * width() == mineCount())

    private fun height() = property.height.value
    private fun width() = property.width.value
    private fun mineCount() = property.mineCount.value

    data class Property(
        val height: Height,
        val width: Width,
        val mineCount: MineCount,
    ) {
        init {
            require(height.value * width.value >= mineCount.value) { "지뢰의 갯수가 전체 타일의 갯수보다 크거나 같을수없습니다."}
        }
    }
}
