package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> addPropertyOffer(@RequestBody PropertyOffer propertyOffer) {
        PropertyOffer addedPropertyOffer = iPropertyOfferService.addPropertyOffer(propertyOffer);
        return new ResponseEntity<>(addedPropertyOffer, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllPropertyOffers() {
        List<PropertyOffer> propertyOffers = iPropertyOfferService.getAllPropertyOffers();
        return new ResponseEntity<>(propertyOffers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPropertyOfferById(@PathVariable int id) {
        PropertyOffer propertyOffer = iPropertyOfferService.getPropertyOffer(id);
        if (propertyOffer != null) {
            return new ResponseEntity<>(propertyOffer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Property offer not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePropertyOffer(@PathVariable int id, @RequestBody PropertyOffer updatedPropertyOffer) {
        PropertyOffer propertyOffer = iPropertyOfferService.updatePropertyOffer(id, updatedPropertyOffer);
        if (propertyOffer != null) {
            return new ResponseEntity<>(propertyOffer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Property offer not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePropertyOffer(@PathVariable int id) {
        iPropertyOfferService.deletePropertyOffer(id);
        return new ResponseEntity<>("Property offer deleted", HttpStatus.OK);
    }

}