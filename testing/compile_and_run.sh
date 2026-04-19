#!/bin/bash
# ============================================================
#  compile_and_run.sh  —  For Linux/Mac (Nobara, Ubuntu, Fedora, etc.)
#
#  SETUP (run once):
#  1. Install Java if needed:
#       sudo dnf install java-17-openjdk-devel   (Fedora)
#       sudo apt install openjdk-17-jdk-headless (Ubuntu)
#
#  2. Download the SQLite JDBC driver if you don't have it:
#       wget https://github.com/xerial/sqlite-jdbc/releases/download/3.45.1.0/sqlite-jdbc-3.45.1.0.jar
#
#  3. Download JUnit 5 standalone jar (for tests):
#       wget "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar"
#
#  4. Make this script executable:
#       chmod +x compile_and_run.sh
#
#  USAGE:
#       ./compile_and_run.sh          — compile + launch GUI
#       ./compile_and_run.sh tests    — compile + run ValidatorTest
#       ./compile_and_run.sh console  — compile + launch console menu
# ============================================================

# --- Jar paths (edit if your jar filenames differ) ---
SQLITE_JAR=$(ls sqlite-jdbc-*.jar 2>/dev/null | head -1)
JUNIT_JAR="junit-platform-console-standalone.jar"

if [ -z "$SQLITE_JAR" ]; then
    echo "[ERROR] No sqlite-jdbc jar found in this directory."
    echo "        Download it with:"
    echo "        wget https://github.com/xerial/sqlite-jdbc/releases/download/3.45.1.0/sqlite-jdbc-3.45.1.0.jar"
    exit 1
fi

CP="$SQLITE_JAR:."

echo "=== Compiling all Java files ==="
javac -cp "$CP" code/*.java
if [ $? -ne 0 ]; then
    echo ""
    echo "[ERROR] Compilation failed. Fix the errors above first."
    exit 1
fi

echo "=== Compiling test files ==="
javac -cp "$CP:code" testing/*.java
if [ $? -ne 0 ]; then
    echo ""
    echo "[ERROR] Test compilation failed. Fix the errors above first."
    exit 1
fi
echo "[OK] Compilation successful."
echo ""

MODE="${1:-gui}"

if [ "$MODE" = "tests" ]; then:testing
    if [ ! -f "$JUNIT_JAR" ]; then
        echo "[ERROR] JUnit jar not found: $JUNIT_JAR"
        echo "        Download it with:"
        echo '        wget "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar" -O junit-platform-console-standalone.jar'
        exit 1
    fi
    echo "=== Running ValidatorTest ==="
    java -jar "$JUNIT_JAR" --class-path "$SQLITE_JAR:code" --select-class ValidatorTest

elif [ "$MODE" = "console" ]; then
    echo "=== Launching Console Interface ==="
    java -cp "$CP:code" Main

else
    echo "=== Launching GUI ==="
    java -cp "$CP:code" GymGui
fi
