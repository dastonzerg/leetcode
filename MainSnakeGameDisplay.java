import static java.lang.System.out;
import java.util.*;

public class MainSnakeGameDisplay {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		out.println("PLease enter your width and height: ");
		int width = sc.nextInt();
		int height = sc.nextInt();
		SnakeGameDisplay snake = new SnakeGameDisplay(width, height);
		snake.display();

		String in = sc.next();
		while (!in.equals("q")) {
			if (snake.move(in) == -1) {
				break;
			}
			snake.display();
			in = sc.next();
		}

		out.println("Game Over. You are dead.");
	}
}
