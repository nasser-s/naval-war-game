# simple-game
Simple multiplayer game

<h3>Quick start:</h3>
    <ul>
    <li>Build project with maven : <code>mvn clean install package</code></li>
    <li>Open terminal and  go to target folder</li>
    <li>run: <code>java -jar websocket-0.1.0.jar</code></li>
    <li>Open <code>http://localhost:8090/</code> in browser</li>
    <li>Both players must connect to server</li>
    <li>Two ships should appear in game canvas</li>
    <li>Click on canvas (in some browsers this action is required to receive user key press)</li>
    <li>User <b>arrow keys</b> to change ship direction and speed</li>
    <li>Use <b>'X'</b> to fire torpedo</li>    
    </ul>      
    <h3>Detailed information about implementation exist in the file <a href="https://github.com/nasser-s/naval-war-game/blob/master/doc/doc.odt">'doc/doc.odt'</a></h3>
<h3>To do:</h3>
<ul>
<li>Export all literals into property file</li>
<li>Add more testes</li>
<li>More precise logging</li>
<li>Fix windows close bug in chromium</li>
<li>Improve game restart operation and persist prev user scores</li>
<li>More than two players</li>
<li>Report ship informations</li>
<li>Add facility to define game boards</li>
<ul> 
