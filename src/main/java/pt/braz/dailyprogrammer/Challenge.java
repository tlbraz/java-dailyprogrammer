package pt.braz.dailyprogrammer;

public abstract class Challenge {

    private final String url;

    public void execute() {
        System.out.println("Executing challenge for " + url);
        tests();
        System.out.println("Success!");
    }

    public abstract void tests();

    public Challenge(String url) {
        this.url = url;
    }

}
