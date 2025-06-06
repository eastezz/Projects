public class Organization implements IOwner {
    private String name;

    public Organization(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
