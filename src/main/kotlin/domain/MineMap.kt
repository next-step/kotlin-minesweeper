package domain

class MineMap(
    val elements: List<List<MapElement>>
) {
    fun isMine(location: Location): Boolean {
        if (location.row < 0 || location.row >= elements.size || location.column < 0 || location.column >= elements[0].size) return false

        return elements[location.row][location.column] is Mine
    }
}
