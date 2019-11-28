import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Initialize the game and handle the interaction between player and all game 
 * ALso decide when to finish the game 
 * @author Zhe Ji, Junyuan Chen
 * 
 */
public class Jump_IN_Controller extends DefaultHandler {
	
	private Piece f1, f2, m1, m2, r1, r2, r3;  
	private Jump_IN_View view;
	private Jump_IN_Model model; 
	private int level = -1;
	private boolean readlevel, readf1, readf2, readm1, readm2, readr1, readr2, readr3 = false;  
	private String levelName;
	
	public Jump_IN_Controller (Jump_IN_View view, Jump_IN_Model model) {
		
		this.view = view;
		this.model = model;
		f1 = new Fox (Piece.pieceName.F1); 
		f2 = new Fox (Piece.pieceName.F2); 
		r1 = new Rabbit (Piece.pieceName.R1);
		r2 = new Rabbit (Piece.pieceName.R2);
		r3 = new Rabbit (Piece.pieceName.R3);
		m1 = new Mushroom(Piece.pieceName.M1);
		m2 = new Mushroom(Piece.pieceName.M2);
		this.view.addChooseLevelButtonListener(new chooseLevelButtonListener());
		this.view.addNewGameButtonListener(new newGameButtonListener());
		this.view.addUndoButtonListener(new UndoButtonListener());
		this.view.addRedoButtonListener(new RedoButtonListener());
		this.view.addHintButtonListener(new HintButtonListener());
		this.view.addSaveButtonListener(new SaveButtonListener());
		this.view.addLoadButtonListener(new LoadButtonListener());
		for (int x=0; x<5; x++) {
			for (int y=0; y<5; y++) {
				this.view.addGridButtonListener(x, y, new GridButtonListener(new Tuple(x, y)));
			}
		}
		
	}
		
	/**
	 * Method to start the game
	 */
	public void play () {
		try {
			levels(this.level);
			this.readlevel = false;
			this.view.setupButtons(this.model.setupBoard());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setLevel (int level) {
		this.level = level;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals(this.levelName)) {
			this.readlevel = true;
		} else if (qName.equals("f1")) {
			this.readf1 = true;
		} else if (qName.equals("f2")) {
			this.readf2 = true;
		} else if (qName.equals("m1")) {
			this.readm1 = true;
		} else if (qName.equals("m2")) {
			this.readm2 = true;
		} else if (qName.equals("r1")) {
			this.readr1 = true;
		} else if (qName.equals("r2")) {
			this.readr2 = true;
		} else if (qName.equals("r3")) {
			this.readr3 = true;
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
	      
		if (qName.equalsIgnoreCase(this.levelName)) {
	    	 this.readlevel = false;
		}
	}
	   
		
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (readf1) {
			if (readlevel) {
				setPieceLocation(this.f1, new String(ch, start, length));
			}
			readf1 = false;
		} else if (readf2) {
			if (readlevel) {
				setPieceLocation(this.f2, new String(ch, start, length));
			}
			readf2 = false;
		} else if (readm1) {
			if (readlevel) {
				setPieceLocation(this.m1, new String(ch, start, length));
			}
			readm1 = false;
		} else if (readm2) {
			if (readlevel) {
				setPieceLocation(this.m2, new String(ch, start, length));
			}
			readm2 = false;
		} else if (readr1) {
			if (readlevel) {
				setPieceLocation(this.r1, new String(ch, start, length));
			}
			readr1 = false;
		} else if (readr2) {
			if (readlevel) {
				setPieceLocation(this.r2, new String(ch, start, length));
			}
			readr2 = false;
		} else if (readr3) {
			if (readlevel) {
				setPieceLocation(this.r3, new String(ch, start, length));
			}
			readr3 = false;
		}
		
	 } 
	
	private void setPieceLocation (Piece piece, String location) {
		if (piece.getName().toString().startsWith("F")) {
			int [] numbers = new int[4];
			String[] temp = location.split("");
			for (int i=0; i<4; i++) {
				numbers[i] = Integer.parseInt(temp[i]);
			}
			Tuple place = new Tuple (numbers[0], numbers[1], numbers[2], numbers[3]);
			this.model.putPiece(piece, place);
		} else {
			int [] numbers = new int[2];
			String[] temp = location.split("");
			for (int i=0; i<2; i++) {
				numbers[i] = Integer.parseInt(temp[i]);
			}
			Tuple place = new Tuple (numbers[0], numbers[1]);
			this.model.putPiece(piece, place);
		} 
	}
	/**
	 * Method to store the piece initial location on the board 
	 * For now we just have one level, set it to one by default
	 * @param level
	 */
	private void levels (int level) throws IOException{

		this.levelName = "level"+ Integer.toString(level);
		
		try {
		    File file = new File(System.getProperty("user.dir") + "/levels.txt");
		 	SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(file, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public class GridButtonListener implements ActionListener {
		private Tuple pieceLocation; 
		private Piece piece;
		
		public GridButtonListener (Tuple location) {
			this.pieceLocation = location;
		}
		@Override
		public void actionPerformed(ActionEvent e) {

			if (model.hasSelected()!=null && (model.hasSelected().toString().startsWith("F") || model.hasSelected().toString().startsWith("R"))) { // if a fox has selected, then move the piece
				
				if (model.hasSelected().toString().equals("F1")) {
					this.piece = f1;
				} else if (model.hasSelected().toString().equals("F2")) {
					this.piece = f2;				
				} else if (model.hasSelected().toString().equals("R1")) {
					this.piece = r1;
				} else if (model.hasSelected().toString().equals("R2")) {
					this.piece = r2;
				} else if (model.hasSelected().toString().equals("R3")) {
					this.piece = r3;
				}
				if (model.movePiece(this.piece, this.pieceLocation)) {//valid move, notify view
					//view.cleaeHintText();
					model.cleanRedo();
					model.setSelectedPiece(null);
					view.setSelected("");
					view.setupButtons(model.setupBoard());
					if (model.isFinished()) {
						view.showDialog("Game finished, you win!");
						view.cleaeHintText();
						play();					
					}
				} else { //invalid move, show dialog
					view.showDialog("Invalid Move");
					model.setSelectedPiece(null);
					view.setSelected("");
				}
				
			} else { // set selected in model
				if (model.getPiece(pieceLocation) != null) {
					Piece.pieceName name = model.getPiece(this.pieceLocation);
					model.setSelectedPiece(name);
					view.setSelected(name.toString());
				} else {
					view.showDialog("This item is not allowed to move.");
				}
		
			}
			
		}
	
	}
	
	public class newGameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (level == -1) {
				view.setLevelLabel("Please set level first.", Color.RED);
			} else {
				play();
				view.cleaeHintText();
			}
			
		}
		
	}
	
	public class chooseLevelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setLevel(view.levelInputDialog());
			if (level >0) {
				view.setLevelLabel("Level: " + String.valueOf(level), Color.BLUE);
			}
		}
		
	}
	
	public class UndoButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model.rollBack()) {
				view.setupButtons(model.setupBoard());
			} else {
				view.showDialog("Can not undo righr now.");
			}
			
		}
		
	}
	
	public class RedoButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model.goForward()) {
				view.setupButtons(model.setupBoard());
			} else {
				view.showDialog("Can not redo righr now.");
			}
			
		}
		
	}
	
	public class HintButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.cleaeHintText();
			String hint = model.findSolver();
			view.setHintText(hint, Color.RED);
		}
		
	}
	
	public class SaveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				FileOutputStream fout = new FileOutputStream(System.getProperty("user.dir") + "/Jump_In_Game_Status.txt");
				ObjectOutputStream out  =new ObjectOutputStream(fout);
				out.writeObject(model);
				out.close();
				fout.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
		}
		
	}
	
	public class LoadButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "/Jump_In_Game_Status.txt");
				ObjectInputStream objectFile = new ObjectInputStream(fin); 
				Jump_IN_Model newModel = (Jump_IN_Model) objectFile.readObject();
				objectFile.close();
				fin.close();
				model = new Jump_IN_Model();
				model = newModel;
				view.setupButtons(model.setupBoard());
			} catch (IOException | ClassNotFoundException e3) {
				e3.printStackTrace();
			}
			
		}
		
	}

}