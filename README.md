# JUMP_IN_GAME

Github website: https://github.com/hate-bug/JUMP_IN_GAME

PURPOSE OF PROJECT: To design a "Jump IN" board game. 

VERSION or DATE: November 17th, 2019

AUTHORS:Zhe Ji, Xiling Wang, Jiawei Ma, Defa Hu, Junyuan Chen

USER INSTRUCTIONS:
		
		1. In the command window, run the java program: java -jar Jump_IN.jar
		2. The game should start and user can see a board with a 5*5 grid 
		3. Choose a level first, go to the level and click the choose level. 
		4. Enter the level in the dialog, only 2 levels are available currently, we will continue working on it.
		5. After choosing the level, a JTextField is indicating the chosen level and then start the game. 
		6.Click the redo and undo button can give the previous status of the board and store the current status, by clicking redo, you can return to the status before you click undo.
		7. A hint button is made for giving all the remaining path needed to win the game. (If you click the button at very start of the game. It will be extremely time consuming and storage consuming because of finding the correct path in tree via listing all possible path by breath-first tree, the time factor depends on the level which is the step left to success. And the size of the tree will raise to power of level number.)
		8. A save button to save the board status to a default path by using serializable. 
		9. A load button to load the path saved in previous save method. And put the status in the path which is a board into a new model and set up the new board.
		10. create a xml file and using SAX parser to convert the XML file into a actual board and set it up.
		11. Enjoy :) 

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
		3. Try to improve efficiency
		4. Solve potential bugs

Java Design: 
		
		1. Change the Piece Interface to abstract class. 
		2. Strictly follow the MVC pattern, which consits Jump_IN_Model, Jump_IN_View, Jump_IN_Controller. 
		3. Jump_IN_Model used to store the conditions of the board, determine if user's move is valid or not. 
		4. Jump_IN_View used to display the UI and collect users' gesture. 
		5. Jump_IN_Controller used to transfer user's input to internal methods. 
		6. Using Tuple to represent the location (x, y), (x1, y1, x2, y2)
		7. Jump_IN_Crontroller captures user events from view and use ActionPerformed to notify the model. 
		8. Model return value to verify the backend has already done the change or not and then update to the view. 
		9. Use 2 stacks called history and redoStack, everytime taking a step, store the previous board status to history and after clicking undo, pop the top of the history stack. And store that to the redoStack , in case we want to redo the step, if we click redo, we also need to store it back to the history since it is also a step made.
		10. Use a breath-first tree for every status of board and try to move all possible movable piece. And store all the possibility to the children nodes of this specified status, each level of the tree means the possible moves of each step.(For example, at first level all the piece can be moved once maybe 7 moves that means 7 nodes stored to the root and second step every single node in first level may have 7 possible moves so it will be 49 nodes and each 7 of them store into that specified first-step moved node.) Then do a recursive process until we find the answer and keep tracking of the answer’s parent node to get the instruction we needed. If there is no solution, that means you have to undo because we cannot make any further move to finish the game. In conclusion, as the announcement by professor if we want to get all the possible path, the scale of the tree will increment in the power of level size. It will be extremely time and storage consuming.
		11. using serializable method (outputsteam and inputstrem) to implement save and load method.
		12. using SAX parser to convert XML to a java readable class to implement the board.
		
