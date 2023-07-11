package domain

class Basic : Cell() {
    var count = 0
        private set

    fun addCount(count: Int) {
        this.count += count
    }
}
