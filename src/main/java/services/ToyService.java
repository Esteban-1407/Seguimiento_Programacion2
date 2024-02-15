package services;

import mapping.dtos.ToyDTO;
import model.ToyType;

import java.util.List;
import java.util.Map;

public interface ToyService {
    void addToy(ToyDTO toy) throws Exception;
    Map<ToyType,Integer> showByType() throws Exception;
    int showTotalCountToy() throws Exception;
    double showTotalValueToys() throws Exception;
    void decreaseQuantityToy(String toyName, int quantity) throws Exception;
    void increaseQuantityToy(String toyName, int quantity) throws Exception;
    Map.Entry<ToyType,Integer> minToy() throws Exception;
    Map.Entry<ToyType,Integer> maxToy() throws Exception;
    List<ToyDTO> filterToysByPrice(double minPrice) throws Exception;
    List<ToyDTO> sortToysByQuantity() throws Exception;

}
