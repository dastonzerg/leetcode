import java.util.*;

// 353 Design Snake Game
// ***Start***
public class SnakeGame {
	
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
	
    int _width;
	int _height;
	Deque<Position> _snake=new LinkedList<>();
	Queue<Position> _foods=new LinkedList<>();
	boolean[][] _board;
    
    public SnakeGame(int width, int height, int[][] food) {
        _width=width;
		_height=height;
		_snake.add(new Position(0, 0));
		for(int[] foodPos:food) {
			_foods.add(new Position(foodPos[0], foodPos[1]));
		}
		_board=new boolean[height][width];
    }
	
	public int move(String direction) {
		Position newHead;
		Position oldHead=_snake.getFirst();
		if(direction.equals("U")) {
			newHead=new Position(oldHead._x-1, oldHead._y);
		}
		else if(direction.equals("D")) {
			newHead=new Position(oldHead._x+1, oldHead._y);
		}
		else if(direction.equals("L")) {
			newHead=new Position(oldHead._x, oldHead._y-1);
		}
		else {  // "R"
			newHead=new Position(oldHead._x, oldHead._y+1);
		}
		
		if(newHead._x==-1 || newHead._x==_height || newHead._y==-1 || 
				newHead._y==_width) {
			return -1;
		}
		
		// 先去尾再加头
		if(!_foods.isEmpty() && newHead.isEqual(_foods.peek())) {
			_foods.poll();
		}
		else {
			Position tail=_snake.removeLast();
			_board[tail._x][tail._y]=false;
		}
		if(_board[newHead._x][newHead._y]==true) {
			return -1;
		}
		_snake.addFirst(newHead);
		_board[newHead._x][newHead._y]=true;
		
		return _snake.size()-1;
	}
}

// ****end****
