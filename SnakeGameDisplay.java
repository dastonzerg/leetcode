import java.util.*;
import static java.lang.System.out;

public class SnakeGameDisplay {
	
	private class Position {
		int _x;
		int _y;
		
		Position(int x, int y) {
			_x=x;
			_y=y;
		}
		
		boolean isEqual(Position anotherPos) {
			return _x==anotherPos._x && _y==anotherPos._y;
		}
	}
	
	int _score=0;
	int _width;
	int _height;
	Deque<Position> _snake=new LinkedList<>();
	boolean[][] _board;
	Random random=new Random();
	Position _food;
	
	public SnakeGameDisplay(int width, int height) {
		_width=width;
		_height=height;
		_snake.add(new Position(0, 0));
		_board=new boolean[height][width];
		_board[0][0]=true;
		do {
			_food=new Position(random.nextInt(_height), random.nextInt(_width));
		} while(_board[_food._x][_food._y]==true);
	}
	
	public void display() {
		out.println("Board State: ");
		for(int x=0; x<=_height-1; x++) {
			out.print("|");
			for(int y=0; y<=_width-1; y++) {
				if(_board[x][y]==true) {
					if(_snake.getFirst()._x==x && _snake.getFirst()._y==y) {
						out.print("#|");
					}
					else {
						out.print("O|");
					}
				}
				else if(_food._x==x && _food._y==y) {
					out.print("X|");
				}
				else {
					out.print(" |");
				}
			}
			out.println("");
		}
		out.println("Score: "+_score);
	}
	
	public int move(String direction) {
		Position newHead;
		Position oldHead=_snake.getFirst();
		
		if(direction.equals("w")) {
			newHead=new Position(oldHead._x-1, oldHead._y);
		}
		else if(direction.equals("s")) {
			newHead=new Position(oldHead._x+1, oldHead._y);
		}
		else if(direction.equals("a")) {
			newHead=new Position(oldHead._x, oldHead._y-1);
		}
		else if(direction.equals("d")) {  // "R"
			newHead=new Position(oldHead._x, oldHead._y+1);
		}
		else {
			return -1;
		}
		
		if(newHead._x==-1 || newHead._x==_height || newHead._y==-1 || 
				newHead._y==_width) {
			return -1;
		}
		
		if(!newHead.isEqual(_food)) {
			Position tail=_snake.removeLast();
			_board[tail._x][tail._y]=false;
		}
		else {
			do {
				_food=new Position(random.nextInt(_height), random.nextInt(_width));
			} while(_board[_food._x][_food._y]==true);
		}
		
		if(_board[newHead._x][newHead._y]==true) {
			return -1;
		}
		
		_snake.addFirst(newHead);
		_board[newHead._x][newHead._y]=true;
		
		_score=_snake.size()-1;
		
		return _score;
	}
}
