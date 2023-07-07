package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.PropertyOffer;

import java.util.List;

public interface IPropertyOfferService {
    PropertyOffer addPropertyOffer(PropertyOffer propertyOffer, int idUser);
    List<PropertyOffer> getAllPropertyOffers();
    PropertyOffer getPropertyOffer (int id);
    PropertyOffer updatePropertyOffer(int id, PropertyOffer propertyOffer);
    void deletePropertyOffer(int id);

}
