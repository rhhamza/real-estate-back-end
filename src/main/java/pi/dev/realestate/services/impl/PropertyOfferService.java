package pi.dev.realestate.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.PropertyOfferRepository;
import pi.dev.realestate.repositories.UserRepository;
import pi.dev.realestate.services.interfaces.IPropertyOfferService;

import java.sql.Timestamp;
import java.util.List;
@Service
public class PropertyOfferService implements IPropertyOfferService {
    @Autowired
    PropertyOfferRepository propertyOfferRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public PropertyOffer addPropertyOffer(PropertyOffer propertyOffer, int idUser){
        UserEntity user = userRepository.findById(idUser).orElse(null);
        propertyOffer.setUser(user);
        propertyOfferRepository.save(propertyOffer);
        return propertyOffer;
    }
    @Override
    public List<PropertyOffer> getAllPropertyOffers(){
        return propertyOfferRepository.findAll();
    }
    @Override
    public PropertyOffer getPropertyOffer(int id){
        return propertyOfferRepository.findById(id).orElse(null);
    }

    @Override
    public PropertyOffer updatePropertyOffer(int id, PropertyOffer updatedPropertyOffer) {
        PropertyOffer existingPropertyOffer = propertyOfferRepository.findById(id).orElse(null);
        if (existingPropertyOffer != null) {
            existingPropertyOffer.setReference(updatedPropertyOffer.getReference());
            existingPropertyOffer.setTitle(updatedPropertyOffer.getTitle());
            existingPropertyOffer.setType(updatedPropertyOffer.getType());
            existingPropertyOffer.setCategory(updatedPropertyOffer.getCategory());
            existingPropertyOffer.setPrice(updatedPropertyOffer.getPrice());
            existingPropertyOffer.setSqm(updatedPropertyOffer.getSqm());
            existingPropertyOffer.setLocation(updatedPropertyOffer.getLocation());
            existingPropertyOffer.setBedrooms(updatedPropertyOffer.getBedrooms());
            existingPropertyOffer.setBathrooms(updatedPropertyOffer.getBathrooms());
            existingPropertyOffer.setDescription(updatedPropertyOffer.getDescription());
            existingPropertyOffer.setPicture(updatedPropertyOffer.getPicture());
            existingPropertyOffer.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            propertyOfferRepository.save(existingPropertyOffer);
        }
        return existingPropertyOffer;
    }

    @Override
    public void deletePropertyOffer(int id) {
        propertyOfferRepository.deleteById(id);
    }

}
