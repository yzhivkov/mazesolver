import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class Maze {

	public static class MazeData {
		public Location currentCell;
	}
	public static class Location {
		private static final String BLOCKED = "BLOCKED";
		public String note;
		public int x;
		public int y;
		public String mazeGuid;
		public boolean atEnd;
		public boolean previouslyVisited;
		public String east;
		public String west;
		public String north;
		public String south;
		
		public boolean isBlocked(Direction direction) {
			switch (direction) {
			case EAST:
				return BLOCKED.equals(east);
			case WEST:
				return BLOCKED.equals(west);
			case NORTH:
				return BLOCKED.equals(north);
			case SOUTH:
				return BLOCKED.equals(south);
				
			}
			throw new IllegalStateException("Cannot continue in any direction!");
		}
		@Override
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("x", x)
			.append("y", y)
			.append("visited", previouslyVisited)
			.append("east", east)
			.append("west", west)
			.append("north", north)
			.append("south", south)
			.append("note", note)
			.toString();
		}
	}

	private RestClient restClient = new RestClient();
	
	private Location location;

	void init() {
		updateLocation(restClient.getServerData("http://epdeveloperchallenge.com/api/init", 
				new HashMap<String, String>(), MazeData.class));
	}

	Location currentLocation() {
		return location;
	}
	
	boolean canGo(Direction direction) {
		return !location.isBlocked(direction);
	}

	boolean visitedBefore() {
		return location.previouslyVisited;
	}

	Location go(Direction direction) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mazeGuid", location.mazeGuid);
		params.put("direction", direction.toString());
		return updateLocation(restClient.getServerData("http://epdeveloperchallenge.com/api/move", params, MazeData.class));
	}

	private Location updateLocation(MazeData data) {
		this.location = data.currentCell;
		return location;
	}

	public boolean atEnd() {
		return location.atEnd;
	}
}
