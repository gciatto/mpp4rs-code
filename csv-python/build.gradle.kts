tasks.create<Copy>("createCoreJar") {
    group = "Python"
    val shadowJar by project(":csv-core").tasks.getting(Jar::class)
    dependsOn(shadowJar)
    from(shadowJar.archiveFile) {
        rename(".*?\\.jar", "csv.jar")
    }
    into(projectDir.resolve("jcsv/jvm"))
}

fun findExecutablePath(name: String, vararg otherNames: String): File? {
    val names = listOf(name, *otherNames).flatMap { listOf(it, it + ".exe") }
    return System.getenv("PATH")
        .split(File.pathSeparatorChar)
        .asSequence()
        .map { File(it) }
        .flatMap { path -> names.asSequence().map { path.resolve(it) } }
        .filter { it.exists() }
        .firstOrNull()
}

val python = findExecutablePath("python3", "python")?.absolutePath
    ?: error("Cannot find Python executable")

fun pyTask(name: String, vararg args: String, conf: Exec.() -> Unit = {}) =
    tasks.create<Exec>(name) {
        workingDir(projectDir)
        group = "Python"
        commandLine(python, *args)
        conf(this)
    }

pyTask("restoreDeps", "-m", "pip", "install", "-r", "requirements.txt")

pyTask("pythonTest", "-m", "unittest") {
    dependsOn("restoreDeps")
    dependsOn("createCoreJar")
}

tasks.create("test") {
    group = "Verification"
    dependsOn("pythonTest")
}

tasks.create("check") {
    group = "Verification"
    dependsOn("test")
}

exec {
    println("$python --version")
    commandLine(python, "--version")
}
