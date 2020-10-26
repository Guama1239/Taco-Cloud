package tacos;
//domain class to model the ingredients
import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    // static keyword below is not needed because when it is nested inside of a class
    // the static keyword is default so it is not needed!
    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;
    }
}
