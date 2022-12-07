package day_7

class Directory(val name: String, val parentDirectory: Directory?) {
    var directories: MutableMap<String, Directory> = mutableMapOf()
    var size: Int = 0

    fun getTotalSize(): Int {
        var totalSize = size
        directories.forEach { directory ->
            totalSize += directory.value.getTotalSize()
        }
        return totalSize
    }
}