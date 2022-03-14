cd libraries
set ProjectPath = %~dp0
java -jar -Dwebdriver.chrome.driver="F:\Code test automation\07 - Hybrid Framework OrangeHRM\hybrird-framework-orangehrm\driverBrowsers\chromedriver.exe" selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.1.8:4444/grid/register/ -port 5555
pause