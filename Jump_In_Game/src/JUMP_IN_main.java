/**
 * Initiate the game
 * @author zheji
 *
 */

public class JUMP_IN_main {

	public static void main (String[] args) {
		// TODO Auto-generated method stub
		//Game game = new Game();
		//game.play();
		Jump_IN_View view = new Jump_IN_View();
		Jump_IN_Model model = new Jump_IN_Model();
		Jump_IN_Controller controller = new Jump_IN_Controller(view, model);
	}

}