package domain

data class Location(val y: Int, val x: Int) {
    fun isSame(x: Int, y: Int): Boolean {
        return this.x == x && this.y == y
    }
}
