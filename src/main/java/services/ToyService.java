package services;

import mapping.dtos.ToyDTO;
import model.ToyType;

import java.util.List;
import java.util.Map;

public interface ToyService {
    void addToy(ToyDTO toy) throws Exception;
    Map<ToyType,Integer> showByType() throws Exception;
    int showTotalCountToy();
    double showTotalValueToys();
    void decreaseQuantityToy(String toyName, int quantity) throws Exception;
    void increaseQuantityToy(String toyName, int quantity) throws Exception;
    Object maxToy() throws Exception;
    Object minToy() throws Exception;
    List<ToyDTO> filterToysByPrice(double minPrice);
    List<ToyDTO> sortToysByQuantity();

}
