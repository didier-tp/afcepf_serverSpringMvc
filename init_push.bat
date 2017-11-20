cd /d "%~dp0"
git init
git add *
git commit -a -m "version initiale"
git remote add gitHubOriginAfcepfServerSpringMvc https://didier-tp:pwd007!@github.com/didier-tp/afcepf_serverSpringMvc.git
git push -u gitHubOriginAfcepfServerSpringMvc master
pause

REM open with text editor
REM opne with system editor