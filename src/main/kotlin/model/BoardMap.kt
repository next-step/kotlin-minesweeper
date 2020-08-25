package model

class BoardMap(private val value: Map<Coordinates, MineType>) {
    val coordinates = value.keys
    val mineTypes = value.values
    val size = value.size

    fun getValue(coordinates: Coordinates): MineType =
        value.filterKeys { it == coordinates }.values.let {
            if (it.isEmpty()) MineType.ZERO else it.first()
        }

    fun getCount(mineType: MineType) = value.count { it.value == mineType }

    fun containsValue(mineType: MineType) = value.containsValue(mineType)
}
