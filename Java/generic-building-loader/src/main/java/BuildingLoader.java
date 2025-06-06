import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class BuildingLoader 
{
    public static Building<? extends IOwner> loadFromFile(String filename) throws IOException, InvalidFileFormatException, DuplicateRoomException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String header = reader.readLine();
            if(header == null)
            {
                throw new InvalidFileFormatException("Empty File");
            }

            String h[] = header.split(",",-1);
            if(h.length != 5)
            {
                throw new InvalidFileFormatException("First line must have 5 fields" + header);
            }

            String bName = h[0].trim();
            String ownerName = h[1].trim();
            String ownerType = h[2].trim();
            int rows, cols;
            try
            {
                rows = Integer.parseInt(h[3].trim());
                cols = Integer.parseInt(h[4].trim());
            }
            catch(NumberFormatException e)
            {
                throw new InvalidFileFormatException("Invalid row/col in first line " + header, e);
            }

            IOwner owner;
            switch(ownerType)
            {
                case "Person":
                    owner = new Person(ownerName);
                    break;

                case "Company":
                    owner = new Company(ownerName);
                    break;

                case "Organization":
                    owner = new Organization(ownerName);
                    break;
                
                default:
                    throw new InvalidFileFormatException("Unknown owner type '" + ownerType + "'");
            }

            Building<IOwner> building = new Building<>(bName, owner, rows, cols);

            String line;
            int lineNum = 1;
            while((line = reader.readLine()) != null)
            {
                lineNum++;
                String[] parts = line.split(",",-1);
                if(parts.length != 3)
                {
                    throw new InvalidFileFormatException("Line" + lineNum + "must have 3 fields: " + line);
                }
                int r, c;
                String roomName = parts[2].trim();
                try
                {
                    r = Integer.parseInt(parts[0].trim());
                    c = Integer.parseInt(parts[1].trim());
                }
                catch(NumberFormatException e)
                {
                    throw new InvalidFileFormatException("Invalid coordinates at line " + lineNum + ": " + line, e);
                }
                building.addRoom(r, c, new Room(roomName));
            }
            return building;
        }
    }
}
