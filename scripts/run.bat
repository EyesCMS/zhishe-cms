@echo off
echo.
echo [信息] 运行Web工程。
echo.

cd %~dp0
cd ../cms-core/target

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% cms-core-1.0-SNAPSHOT.jar

cd bin
pause