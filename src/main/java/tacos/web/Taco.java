package tacos.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Taco {

    @NotNull
    private String name;
    private List<String> ingredients;

}

/* We save typing all the lines below by using @Data
    public Taco(String name, List<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Taco)) return false;
        Taco taco = (Taco) o;
        return Objects.equals(name, taco.name) &&
                Objects.equals(ingredients, taco.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }*/
