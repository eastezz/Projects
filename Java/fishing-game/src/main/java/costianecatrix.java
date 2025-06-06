import java.util.Random;

public class costianecatrix implements Fishable
{
    private String name;
    private int weight;
    private int value;
    private boolean isFishable = true;
    public costianecatrix()
    {
        java.util.Random r = new Random();
        this.name = "Costia necatrix";
        this.weight = r.nextInt(3) + 1;
        this.value = -5 * this.weight;
    }
    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public int getValue()
    {
        return this.value;
    }

    @Override
    public int getWeight()
    {
        return this.weight;
    }

    @Override
    public boolean fish()
    {
        if(isFishable)
        {
            isFishable = false;
            return true;
        }
        return false;
    }
}
