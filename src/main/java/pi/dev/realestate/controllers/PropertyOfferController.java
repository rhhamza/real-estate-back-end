package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.services.interfaces.IPropertyOfferService;

@RestController
@RequestMapping("/property/offer")
public class PropertyOfferController {

    @Autowired
    IPropertyOfferService iPropertyOfferService;

    @PostMapping("/add")
    public PropertyOffer addPropertyOffer (@RequestBody PropertyOffer propertyOffer) {
        return iPropertyOfferService.addPropertyOffer(propertyOffer);
    }
}
