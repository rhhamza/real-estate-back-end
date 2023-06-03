package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.services.interfaces.IPropertyOfferService;

import java.util.List;

@RestController
@RequestMapping("/property/offer")
public class PropertyOfferController {

    @Autowired
    IPropertyOfferService iPropertyOfferService;

    @PostMapping("/add")
    public PropertyOffer addPropertyOffer (@RequestBody PropertyOffer propertyOffer) {
        return iPropertyOfferService.addPropertyOffer(propertyOffer);
    }

    @GetMapping("/all")
    public List<PropertyOffer> getAllPropertyOffers () {
        return iPropertyOfferService.getAllPropertyOffers();
    }
    @GetMapping("/{id}")
    public PropertyOffer getPropertyOfferById (@PathVariable int id) {
        return iPropertyOfferService.getPropertyOffer(id);
    }



}
