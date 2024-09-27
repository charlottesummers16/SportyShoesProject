package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Shoe;
import com.repository.ShoeRepository;

@Service
public class ShoeService {
	
	@Autowired
	ShoeRepository shoeRepository;
	
	public String createShoe(Shoe shoe) {
		try {
			if (shoeRepository.existsById(shoe.getShoeId())) {
				return "Shoe ID already in use";
			} else {
				String checkShoe = checkShoeIsValid(shoe);
				if (checkShoe.equals("Shoe added")) {
					shoeRepository.save(shoe);
					return "Shoe added";
				}
				return checkShoe;
			}
		} catch (Exception e) {
			return "Shoe could not be added";
		}
	}
//	public boolean createShoe(Shoe shoe) {
//		try {
//			if (shoeRepository.existsById(shoe.getShoeId())) {
//				return false;
//			} else {
//				if (checkShoeIsValid(shoe).equals("Shoe added.")) {
//					shoeRepository.save(shoe);
//					
//				}
//				return true;
//			}
//		} catch (Exception e) {
//			System.out.println("Shoe could not be added");
//			return false;
//		}
//	}
	
	public boolean updateShoe(Shoe shoe) {
		try {
			if (shoeRepository.existsById(shoe.getShoeId())) {
				Shoe dbShoe = shoeRepository.findById(shoe.getShoeId()).get();
				dbShoe.setPrice(shoe.getPrice());
				dbShoe.setQuantityInStock(shoe.getQuantityInStock());
				shoeRepository.saveAndFlush(dbShoe);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Shoe could not be updated");
			return false;
		}
	}
	
	public boolean deleteShoe(int shoeId) {
		try {
			if (shoeRepository.existsById(shoeId)) {
				shoeRepository.deleteById(shoeId);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Shoe could not be deleted");
			return false;
		}
	}
	
	public Shoe findShoeById(int shoeId) {
		Optional<Shoe> result = shoeRepository.findById(shoeId);
		if (result.isPresent()) {
			return result.get();
		} else {
			System.out.println("Shoe could not be found");
			return null;
		}
	}
	
	public List<Shoe> findAllShoes() {
		return shoeRepository.findAll();
	}
	
	public List<Shoe> findShoesByBrand(String brand) {
		return shoeRepository.findShoeByBrand(brand);
	}
	
	public List<Shoe> findShoesBySearch(String brand, String type) {
		if (!brand.isBlank() && !type.isBlank()) {
			return shoeRepository.findShoeByBrandAndType(brand, type);
		} else if (!brand.isBlank()) {
			return shoeRepository.findShoeByBrand(brand);
		} else {
			return shoeRepository.findShoeByType(type);
		}
	}
	
	private String checkShoeIsValid(Shoe shoe) {
		if (shoe.getShoeId() < 1) {return "Invalid ID";}
		if (shoe.getBrand().length() < 4) {return "Invalid brand, must be at least 4 characters";}
		if (shoe.getType().length() < 4) {return "Invalid type, must be at least 4 characters";}
		if (shoe.getPrice() <= 0) {return "Invalid price, must be greater than 0";}
		return "Shoe added";
	}

}
