package objects;

public class BallPrototype {
    private final Ball template;

    public BallPrototype(Ball template) {
        this.template = template;
    }

    public Ball cloneBall() {
        return template.clone();
    }
}
