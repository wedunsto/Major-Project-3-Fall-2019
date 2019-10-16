# Major-Project-3-Fall-2019
Design a 2D fighting game in Java

### **CAUTION : This is not an exhaustive flow chart. These are not exhaustive UMLs. You may need to implement additional class and methods!**

![Project UML](https://github.com/wedunsto/Major-Project-3-Fall-2019/blob/master/ProjectUMLUpdated.PNG)

The Animation interface contains a method 'move()' that a class can implement.\
This method will be overwritten, and provide instruction on how to move fighters\
![Animation UML](https://github.com/wedunsto/Major-Project-3-Fall-2019/blob/master/AnimationUMLUpdated.PNG)

The Asset class contains properties that fighters contain, such as speed, direction, and power\
![Asset UML](https://github.com/wedunsto/Major-Project-3-Fall-2019/blob/master/AssetUMLUpdated.PNG)

The Fighter class can be implemented in **many ways**\
This is where properties such as sprites, different viewports for animation, and the move() method is overwritten\
![Fighters UML](https://github.com/wedunsto/Major-Project-3-Fall-2019/blob/master/FightersUMLUpdated.PNG)

The FightingStage class is where the fighting stage background is imported.\
Utilize this class' constructor to import the fighting stage onto the GameInterface\
![Fighting Stage UML](https://github.com/wedunsto/Major-Project-3-Fall-2019/blob/master/FightingStageUMLUpdated.PNG)

The GameInterface class is where all the elements come together. The fighters are on the stage, the stage is on the game interface. The buttons to start, restart, and exit the game on the game interface, the status, including the players health and time remaining, are also on the game interface.
![Game Interface UML](https://github.com/wedunsto/Major-Project-3-Fall-2019/blob/master/GameInterfaceUMLUpdated.PNG)

The ControlPanel class is where the button to start, restart, and exit the game are created.\
![Control Panel UML](https://github.com/wedunsto/Major-Project-3-Fall-2019/blob/master/ControlPanelUMLUpdated.PNG)

The FightingMechanic class can be implemented in **many ways** \
This is where the results of combat are analyzed. This is where:\
  The health is decremented when a player is hit\
  The time left is evaluated\
  Combat is registered\
![Fighting Mechanics UML](https://github.com/wedunsto/Major-Project-3-Fall-2019/blob/master/FightingMechanicsUMLUpdated.PNG)


## Breakdown: ##

Level 1: Create the FightingStage and GameInterface classes. Import your background image and set the dimensions of your pane. At this level your empty fighting stage should display.
\
Level 2: Create the Asset and Animation classes. These will be used to animate your fighters.
\
Level 3: Create **your** Fighter(s) class(es). Add your fighters to your fighting stage. At this level, your fighting stage should contain your static fighters.
\
Level 4: 
