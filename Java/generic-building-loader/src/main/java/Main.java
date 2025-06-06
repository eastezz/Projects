public class Main {
    public static void main(String[] args)
    {
        try 
        {
            Building<? extends IOwner> b = BuildingLoader.loadFromFile("input.txt");
            System.out.println(b);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
