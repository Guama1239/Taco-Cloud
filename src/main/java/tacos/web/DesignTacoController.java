package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient.Type;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")

public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo){
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("taco") Taco taco, Errors errors, Model model){
        // Prof Trana changed the "design" to "taco", twice in the method signature
        // and once on line 49. Also the changed was made in the view for the object.
        if (errors.hasErrors())
            return "design";
        // Save our taco design
        Taco savedTaco = tacoRepo.save(taco);
        Order order = (Order) model.getAttribute("order");
        order.addDesign(savedTaco);
        log.info("Processing..." + taco);
        return "redirect:/orders/current";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }                      // these are keys ^          , These are their values. ^
    }

    @ModelAttribute(name = "taco")
    public Taco addTacoToModel() {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order addOrderToModel() {
        return new Order();
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}

