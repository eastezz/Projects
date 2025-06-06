public class Company implements IOwner {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
