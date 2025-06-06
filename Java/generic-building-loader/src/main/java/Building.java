public class Building<T extends IOwner> {
    private String name;
    private T owner;
    private Room[][] grid;
    public Building(String name, T owner, int rows, int cols)
    {
        this.name = name;
        this.owner = owner;
        grid = new Room[rows][cols];
    }
    public void addRoom(int row, int col, Room room) throws DuplicateRoomException
    {
        if(grid[row][col] != null)
        {
            throw  new DuplicateRoomException(String.format("Cell [%d][%d] is already occupied by '%s'",row, col, grid[row][col].getName()));
        }
        grid[row][col] = room;
    }




    //DO NOT TOUCH THIS , YOU CAN USE IT FOR TESTING
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Building: ").append(name).append("\n");
        sb.append("Owner: ").append(owner.getName()).append("\n\n");
        sb.append("Rooms:\n");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                String roomName = (grid[i][j] != null) ? grid[i][j].getName() : "Empty";
                sb.append("[").append(i).append("][").append(j).append("] = ").append(roomName).append("\n");
            }
        }

        return sb.toString();
    }
}
