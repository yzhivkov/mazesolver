
public enum Direction {

	EAST("EAST"),
	WEST("WEST"),
	NORTH("NORTH"),
	SOUTH("SOUTH");

	private String text;

	Direction(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

	public Direction right() {
		switch (this) {
		case EAST:
			return SOUTH;
		case WEST:
			return NORTH;
		case NORTH:
			return EAST;
		case SOUTH:
			return WEST;
		}
		return null;
	}

	public Direction left() {
		switch (this) {
		case EAST:
			return NORTH;
		case WEST:
			return SOUTH;
		case NORTH:
			return WEST;
		case SOUTH:
			return EAST;
		}
		return null;
	}

	public Direction opposite() {
		switch (this) {
		case EAST:
			return WEST;
		case WEST:
			return EAST;
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		}
		return null;
	}
}
