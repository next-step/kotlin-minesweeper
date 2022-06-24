package domain

class Place(val number: Int, val placeType: PlaceType, var nearMineCount: Int? = 0) {

    fun isMine(): Boolean {
        return placeType == PlaceType.MINE
    }

    fun isNotMine(): Boolean {
        return !isMine()
    }
}
