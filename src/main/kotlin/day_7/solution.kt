package day_7

import java.io.File

const val fileName = "src/main/resources/day_7/input"
var directories: MutableMap<String, Directory> = mutableMapOf()


fun main(args: Array<String>) {
    println(countSmallFoldersSize())
}

fun countSmallFoldersSize(): Int {
    var size = 0
    val lines = readFileAsLinesUsingUseLines(fileName)
    var currentDirectory = Directory("/", null)
    lines.forEach { line ->
        if (line.contains("\$ cd ")) {
            val name = line.split(' ')[2]
            if (directories.isEmpty()) {
                currentDirectory = Directory(name, null)
                directories[name] = currentDirectory
            } else if (name == "..") {
                currentDirectory = currentDirectory.parentDirectory!!
            } else {
                currentDirectory = currentDirectory.directories[name]!!
            }

        } else if (isNumeric(line.split(' ')[0])) {
            currentDirectory.size += line.split(' ')[0].toInt()
        } else if (line.split(' ')[0] == "dir") {
            currentDirectory.directories[line.split(' ')[1]] = Directory(line.split(' ')[1], currentDirectory)
        }
    }
    val min = 30000000 - (70000000 - directories["/"]!!.getTotalSize())
    //return iDontEvenKnowHowToNameIt(directories)

    return findSmallestFolderToDelete(min, directories)
}

fun findSmallestFolderToDelete(min:Int, directories: MutableMap<String, Directory>): Int {
    var value = Int.MAX_VALUE
    var currentDirectories = directories.flatMap { it.value.directories.entries }
    currentDirectories.forEach { directory ->
        val currentSize = directory.value.getTotalSize()
        if (currentSize in min until value) {
            value = currentSize
        }
    }
    while (!currentDirectories.flatMap { it.value.directories.entries }.isEmpty()) {
        currentDirectories
            .flatMap { it.value.directories.entries }
            .forEach { directory ->
                val currentSize = directory.value.getTotalSize()
                if (currentSize in min until value) {
                    value = currentSize
                }
            }
        currentDirectories = currentDirectories.flatMap { it.value.directories.entries }
    }
    return value
}

fun iDontEvenKnowHowToNameIt(directories: MutableMap<String, Directory>): Int {
    var size = 0
    var currentDirectories = directories.flatMap { it.value.directories.entries }
    currentDirectories.forEach { directory ->
        val currentSize = directory.value.getTotalSize()
        if (currentSize < 100000) {
            size += currentSize
        }
    }
    while (!currentDirectories.flatMap { it.value.directories.entries }.isEmpty()) {
        currentDirectories
            .flatMap { it.value.directories.entries }
            .forEach { directory ->
                val currentSize = directory.value.getTotalSize()
                if (currentSize < 100000) {
                    size += currentSize
                }
            }
        currentDirectories = currentDirectories.flatMap { it.value.directories.entries }
    }
    return size
}

fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
