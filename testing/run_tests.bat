@echo off
REM ============================================================
REM  run_tests.bat  –  Compile and run ValidatorTest with JUnit 5
REM
REM  BEFORE running this script:
REM  1. Download JUnit 5 standalone jar from:
REM     https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar
REM  2. Save it in this folder as: junit-platform-console-standalone.jar
REM  3. Make sure sqlite-jdbc-3.51.1.0.jar is also in this folder
REM ============================================================

SET JUNIT_JAR=junit-platform-console-standalone.jar
SET SQLITE_JAR=sqlite-jdbc-3.51.1.0.jar
SET CP=%JUNIT_JAR%;%SQLITE_JAR%;.

echo === Compiling all source files ===
javac -cp %CP% code\*.java
IF ERRORLEVEL 1 (
    echo [ERROR] Compilation failed. Fix errors above before running tests.
    pause
    exit /b 1
)

echo.
echo === Compiling test files ===
javac -cp %CP%;code testing\*.java
IF ERRORLEVEL 1 (
    echo [ERROR] Test compilation failed. Fix errors above before running tests.
    pause
    exit /b 1
)

echo.
echo === Running ValidatorTest ===
java -jar %JUNIT_JAR% --class-path %CP%;code;testing --select-class ValidatorTest

pause
