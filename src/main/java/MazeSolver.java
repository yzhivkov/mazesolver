import org.apache.commons.lang.time.DurationFormatUtils;



public class MazeSolver {

	public static void main(String[] args) {
		Maze maze = new Maze();
		
		maze.init();
		
		long startTime = System.currentTimeMillis();
		int stepsCount = 0;
		
		Maze.Location location = maze.currentLocation();
		Direction currentDirection = Direction.NORTH;
		
		while (!maze.atEnd()) {
			stepsCount++;
			System.out.println(String.format("Direction: %8s - Location: %s", currentDirection, location));

			// go right if possible based on the current direction
			if (maze.canGo(currentDirection.right())) {
				location = maze.go(currentDirection.right());
				currentDirection = currentDirection.right();
			}
			// go forward if right is not possible
			else if (maze.canGo(currentDirection)) {
				location = maze.go(currentDirection);
			}
			// if not right or forward, let's go left
			else if (maze.canGo(currentDirection.left())) {
				location = maze.go(currentDirection.left());
				currentDirection = currentDirection.left();
			}
			// ok, then... I give up, going back
			else {
				location = maze.go(currentDirection.opposite());
				currentDirection = currentDirection.opposite();
			}
		}
		System.out.println(String.format("Final Location: %s", location));

		System.out.println(String.format("Reached the end with %s steps, for %s", 
				stepsCount, DurationFormatUtils.formatDuration((System.currentTimeMillis() - startTime), "m 'min' s 'sec'")));
	}

}
