package org.service.impl;


import mapping.dtos.ToyDTO;
import model.ToyType;
import org.junit.jupiter.api.Test;
import services.ToyServicelmpl;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ToyServiceImplTest {

        @Test
        public void addToy_Exception() {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toyToAdd = new ToyDTO("Teddy Bear", ToyType.MALE, 20, -5);
            assertThrows(Exception.class, () -> service.addToy(toyToAdd));
        }

        @Test
        public void decreaseQuantityToy_Exception() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            service.addToy(toy1);
            assertThrows(Exception.class, () -> service.decreaseQuantityToy("Car", 5));
        }

        @Test
        public void increaseQuantityToy_Exception() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            service.addToy(toy1);
            assertThrows(Exception.class, () -> service.increaseQuantityToy("Car", -2));
        }

        @Test
        public void maxToy_Exception() {
            ToyServicelmpl service = new ToyServicelmpl();
            assertThrows(Exception.class, service::maxToy);
        }

        @Test
        public void minToy_Exception() {
            ToyServicelmpl service = new ToyServicelmpl();
            assertThrows(Exception.class, service::minToy);
        }

        @Test
        public void filterToysByPrice_Exception() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            service.addToy(toy1);
            assertThrows(Exception.class, () -> service.filterToysByPrice(-10));
        }
        @Test
        public void addToy_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toyToAdd = new ToyDTO("Teddy Bear", ToyType.MALE, 20.0, 5);
            service.addToy(toyToAdd);
            List<ToyDTO> allToys = service.getAllToys();
            assertTrue(allToys.contains(toyToAdd));
        }

        @Test
        public void showByType_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            ToyDTO toy2 = new ToyDTO("Doll", ToyType.FEMALE, 10, 2);
            service.addToy(toy1);
            service.addToy(toy2);
            Map<ToyType, Integer> result = service.showByType();
            assertEquals(2, result.size());
            assertTrue(result.containsKey(ToyType.UNISEX));
            assertTrue(result.containsKey(ToyType.FEMALE));
            assertEquals(1, (int) result.get(ToyType.UNISEX));
            assertEquals(1, (int) result.get(ToyType.FEMALE));
        }

        @Test
        public void showTotalCountToy_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            ToyDTO toy2 = new ToyDTO("Doll", ToyType.FEMALE, 10, 2);
            service.addToy(toy1);
            service.addToy(toy2);
            int totalCount = service.showTotalCountToy();
            assertEquals(5, totalCount);
        }

        @Test
        public void showTotalValueToys_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            ToyDTO toy2 = new ToyDTO("Doll", ToyType.FEMALE, 10, 2);
            service.addToy(toy1);
            service.addToy(toy2);
            double totalValue = service.showTotalValueToys();
            assertEquals(15.0 * 3 + 10.0 * 2, totalValue);
        }

        @Test
        public void decreaseQuantityToy_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            service.addToy(toy1);
            service.decreaseQuantityToy("Car", 2);
            ToyDTO updatedToy = service.getAllToys().get(0);
            assertEquals(1, updatedToy.quantity());
        }

        @Test
        public void increaseQuantityToy_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            service.addToy(toy1);
            service.increaseQuantityToy("Car", 2);
            ToyDTO updatedToy = service.getAllToys().getFirst();
            assertEquals(5, updatedToy.quantity());
        }

        @Test
        public void maxToy_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            ToyDTO toy2 = new ToyDTO("Doll", ToyType.FEMALE, 10, 2);
            service.addToy(toy1);
            service.addToy(toy2);
            Map.Entry<ToyType, Integer> result = service.maxToy();
            assertEquals(ToyType.UNISEX, result.getKey());
            assertEquals(1, result.getValue().intValue());
        }

        @Test
        public void minToy_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            ToyDTO toy2 = new ToyDTO("Doll", ToyType.FEMALE, 10, 2);
            service.addToy(toy1);
            service.addToy(toy2);
            Map.Entry<ToyType, Integer> result = service.minToy();
            assertEquals(ToyType.FEMALE, result.getKey());
            assertEquals(1, result.getValue().intValue());
        }

        @Test
        public void filterToysByPrice_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            ToyDTO toy2 = new ToyDTO("Doll", ToyType.FEMALE, 10, 2);
            service.addToy(toy1);
            service.addToy(toy2);
            List<ToyDTO> filteredToys = service.filterToysByPrice(12);
            assertEquals(1, filteredToys.size());
            assertEquals("Car", filteredToys.getFirst().name());
        }

        @Test
        public void sortToysByQuantity_Successful() throws Exception {
            ToyServicelmpl service = new ToyServicelmpl();
            ToyDTO toy1 = new ToyDTO("Car", ToyType.UNISEX, 15, 3);
            ToyDTO toy2 = new ToyDTO("Doll", ToyType.FEMALE, 10, 2);
            service.addToy(toy1);
            service.addToy(toy2);
            List<ToyDTO> sortedToys = service.sortToysByQuantity();
            assertEquals(2, sortedToys.size());
            assertEquals("Doll", sortedToys.get(0).name());
            assertEquals("Car", sortedToys.get(1).name());
        }
    }



