# CPS2002 Assignment 2020
CPS2002 Software Engineering<br>
Assignment 2020<br>
Mark Mifsud (0382200L)<br>
B.Sc. Mathematics and Computer Science Yr2

__________________________________

### Setup & Execution
The game can be run by executing the `main(String[] args)` method found in *Launcher.java*.
This will start a game with 5 players in 2 teams, playing on a 8x8 safe map.

In order to play the game using a different setup, 
the following code in *Launcher.java* needs to be changed accordingly.

```java
Game myGame = new Game(noOfPlayers, boardWidth, boardHeight, mapType, noOfTeams);
myGame.startGame();
```

`noOfPlayers` - The number of players in the game.<br>
`boardWidth` - The width of the board/map in number of tiles.<br>
`boardHeight` - The height of the board/map in number of tiles.<br>
`mapType` - A string determining the type of map to be played on (Either `"Safe"` or `"Hazard"`).<br>
`noOfTeams` - The number of teams in the game. If you are not playing with teams, set to 0.<br>
<br>

##### Html and Player Output
*Game.java* also contains the following constants which determine the output directory for player files, 
as well as the location of html/css resource files. 
In order to change the output directory, replace these values accordingly.
All resource files should currently be found in the *htmlResources* folder.

**Note:** The path to the CSS file is relative to the output directory, 
whilst both other files are relative to the current working directory.

```java
private final String HTML_OUTPUT_DIR = "playerHtmlOutput";              //Path to output directory, directory must exist
private final String CSS_STYLE_PATH = "../htmlResources/style.css";     //Path to html style (relative to output dir)
private final String HTML_LEGEND_PATH = "htmlResources/mapLegend.html"; //Path to html map legend (relative to working dir)
```

__________________________________

#### v2.0.0 - Map Types, Single Map & Teams
* Different maps types are now available to the player. 
Safe and Hazard maps contain 10% and 30% water tiles respectively.
* Design allows for new map types to be developed when needed.
* Only one instance of each map type can be created.
* The game can be played in teams. 
Players are randomly assigned to teams, and can view areas of the map explored by other players on the same team.
* Added additional tests for old classes and methods to increase branch coverage.

#### v1.0.2 - Arranged Branch Merging
* Release branch for v1.0.1 did not actually contain the new features from develop.
* All features have now been merged into master.

#### v1.0.1 - Arranged Water Tile Action
* When a player lands on a water tile, they will be sent back to the start tile, 
but now all revealed tiles will remain revealed to the player, including the water tile.

#### v1.0.0 - Initial Release
* Game generates a random map with grass, water, and a treasure tile.
* Players can move around the map until they find the treasure tile.
* If a player lands on a water tile, the map is covered, and the player returns to their start position.
* Players can view the map through individual html files that are updated each turn.
* Player input is carried out using the console.
