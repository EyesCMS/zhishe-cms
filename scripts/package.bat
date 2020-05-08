@echo off
echo.
echo [信息] 打包 Web 工程，生成 war/jar 包文件。
echo.

%~d0
cd %~dp0

cd ..
call mvn clean package -Dmaven.test.skip=true

pause