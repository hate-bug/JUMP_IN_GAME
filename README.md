# JUMP_IN_GAME

Github website: https://github.com/hate-bug/JUMP_IN_GAME

PURPOSE OF PROJECT: To design a "Jump IN" board game. 

VERSION or DATE: November 3rd, 2019

AUTHORS:Zhe Ji, Xiling Wang, Jiawei Ma, Defa Hu, Junyuan Chen

USER INSTRUCTIONS:
		
		1. In the command window, run the java program: java -jar Jump_IN.jar
		2. The game should start and user can see a board with a 5*5 grid 
		3. Choose a level first, go to the level and click the choose level. 
		4. Enter the level in the dialog, only 2 levels are available currently, we will continue working on it.
		5. After choosing the level, a JTextField is indicating the chosen level and then start the game. 
		6. Enjoy :) 

GAME RULE: 	

		1. This game has 2 fox(F1, F2), 3 Rabbits (R1, R2, R3) and 2 Mushrooms (M1, M2)
		2. Fox can slide horizontally or vertically. 
		3. Rabbits can jump over one or more items. 
		4. Mushrooms are NOT allowed to move. 
		5. When game finished, you will see a dialog and you can start over again. 


PROJECT FILES:  
		
		1. One UML class diagram. 
         	2. One UML sequence diagram. 
      		3. readme.txt that illustrate the project 
		4. Executable Java program 
  

ToDo list:  	
		
		1. More levels. 
		2. More test cases in Junit test. 

Java Design: 
		
		1. Change the Piece Interface to abstract class. 
		2. Strictly follow the MVC pattern, which consits Jump_IN_Model, Jump_IN_View, Jump_IN_Controller. 
		3. Jump_IN_Model used to store the conditions of the board, determine if user's move is valid or not. 
		4. Jump_IN_View used to display the UI and collect users' gesture. 
		5. Jump_IN_Controller used to transfer user's input to internal methods. 
		6. Using Tuple to represent the location (x, y), (x1, y1, x2, y2)
		
		
