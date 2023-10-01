import jpype
from pathlib import Path
import sys

from jdk4py import JAVA_HOME


CLASSPATH = Path(__file__).parents[0]


def __jvm_lib_file_names():
    if sys.platform == "win32":
        return {"jvm.dll"}
    elif sys.platform == "darwin":
        return {"libjli.dylib"}
    else:
        return {"libjvm.so"}


def __jvmlib(): 
    for name in __jvm_lib_file_names():
        for path in JAVA_HOME.glob(f"**/{name}"):
            path.resolve()
            if path.exists:
                return path
    return None


__jars = [str(j.resolve()) for j in CLASSPATH.glob('*.jar')]


jpype.startJVM(classpath=__jars, jvmpath=str(__jvmlib()))


__jlang = jpype.JPackage("java.lang")
jSystem = __jlang.System


print("start Java version", jSystem.getProperty("java.version"), "from", jSystem.getProperty("java.home"))
print("\tclasspath:", __jars)
