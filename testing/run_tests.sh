#!/bin/bash
# ============================================================
#  run_tests.sh  –  Compile and run ValidatorTest with JUnit 5
#
#  BEFORE running this script:
#  1. Download JUnit 5 standalone jar from:
#     https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar
#  2. Save it in this folder as: junit-platform-console-standalone.jar
#  3. Make sure sqlite-jdbc-3.51.1.0.jar is also in this folder
#  4. Make this script executable: chmod +x run_tests.sh
# ============================================================

JUNIT_JAR="junit-platform-console-standalone.jar"
SQLITE_JAR="sqlite/sqlite-jdbc-3.51.1.0.jar"
CP="$JUNIT_JAR:$SQLITE_JAR:."

echo "=== Compiling all source files ==="
javac -cp "$CP" code/*.java
if [ $? -ne 0 ]; then
    echo "[ERROR] Compilation failed. Fix errors above before running tests."
    exit 1
fi

echo ""
echo "=== Compiling test files ==="
javac -cp "$CP:code" testing/*.java
if [ $? -ne 0 ]; then
    echo "[ERROR] Test compilation failed. Fix errors above before running tests."
    exit 1
fi

echo ""
echo "=== Running ValidatorTest ==="
java -jar "$JUNIT_JAR" --class-path "$CP:code:testing" --select-class ValidatorTest
