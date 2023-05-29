package pi.dev.realestate.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.repositories.PropertyOfferRepository;
import pi.dev.realestate.services.interfaces.IPropertyOfferService;

import java.util.List;
@Service
public class PropertyOfferService implements IPropertyOfferService {
    @Autowired
    PropertyOfferRepository propertyOfferRepository;

    @Override
    public PropertyOffer addPropertyOffer(PropertyOffer propertyOffer){
        propertyOfferRepository.save(propertyOffer);
        return propertyOffer;
    }

    public List<PropertyOffer> getAllPropertyOffers(){
        return propertyOfferRepository.findAll();
    }
    public PropertyOffer getPropertyOffer(int id){
        return propertyOfferRepository.findById(id).orElse(null);
    }

}
