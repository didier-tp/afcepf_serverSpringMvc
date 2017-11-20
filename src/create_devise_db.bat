cd /d "%~dp0"
REM set MYSQL_HOME=C:\Program Files\MySQL\MySQL Server 5.7
set MYSQL_HOME=C:\Program Files (x86)\MySQL\MySQL Server 5.7
"%MYSQL_HOME%\bin\mysql" -u root -p < create_devise_db.sql
pause