class Photo(val name: String)

class PhotoCollection(val photos: MutableList<Photo> = mutableListOf()) : Iterable<Photo> {
    override fun iterator() : Iterator<Photo> = PhotoIterator(photos)
}

class PhotoIterator(val photos: MutableList<Photo> = mutableListOf(), var current: Int = 0) : Iterator<Photo> {
    override fun hasNext(): Boolean = photos.size > current
    override fun next(): Photo {
        val photo = photos[current]
        current++
        return photo
    }
}

fun main(args: Array<String>) {
    val photos = PhotoCollection(mutableListOf(Photo("First Photo"), Photo("Second Photo"), Photo("Last Photo")))
    photos.forEach{ println(it.name) }
}