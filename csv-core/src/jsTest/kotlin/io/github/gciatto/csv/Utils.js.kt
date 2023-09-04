package io.github.gciatto.csv

private val Math: dynamic by lazy {
    js("Math")
}

private val tmpDirectory by lazy {
    tmpdir()
}

actual fun createTempFile(name: String, content: String): String {
    val (n, e) = name.splitExtension()
    val tag = Math.random().toString().replace(".", "")
    val path = "$tmpDirectory/$n-$tag.$e"
    writeFileSync(path, content)
    return path
}
