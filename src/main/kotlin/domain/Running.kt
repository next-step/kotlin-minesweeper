package domain

class Running : Status {
    var stillAlive = true
    override fun next(): Finished {
        return when (stillAlive) {
            true -> FoundAll()
            false -> Boom()
        }
    }
    override fun isFinished() = false
}
