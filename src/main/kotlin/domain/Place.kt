package domain

class Place(
    val number: Int,
    val placeType: PlaceType,
    val nearMineCount: Int? = 0
) {

    fun isMine(): Boolean {
        return placeType == PlaceType.MINE
    }

    fun isNotMine(): Boolean {
        return !isMine()
    }
}
