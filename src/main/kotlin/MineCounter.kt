import domain.MapElement
import domain.MineMap

object MineCounter {
    // mineMap을 입력받아 한 바퀴 순회하면서 cnt 계산
    fun count(mineMap: MineMap) {
        mineMap.elements.forEach {
            it.forEach {
                elementCount(it)
            }
        }
    }

    private fun elementCount(mapElement: MapElement) {
    }
}
