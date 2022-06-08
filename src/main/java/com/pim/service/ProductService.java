package com.pim.service;

import com.pim.dto.ProductCreateDTO;
import com.pim.dto.ProductUpdateDTO;
import com.pim.dto.ResponseDTO;
import com.pim.enums.Authority;
import com.pim.model.Product;
import com.pim.model.dummy.User;
import com.pim.repository.ProductRepository;
//import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class ProductService {
    private ResponseDTO output = new ResponseDTO();
    @Autowired
    private ProductRepository productRepository;

    public ResponseDTO create(ProductCreateDTO input, User requester) {
        Product product = new Product();

        if (requester.hasAuthority(Authority.ROLE_ADMIN)) {
            product = productRepository.findByNameAndStatus(input.getName(), "V");
            if (product == null) {
                product = new Product();
                product.setName(input.getName());
                product.setPrice(input.getPrice());
                //product.setId();
                product.setCategory(input.getCategory());
                product.setQuantity(input.getQuantity());
                product.setStatus("V");
                productRepository.save(product);
            } else {
                return output.generateErrorResponse("Already exist");
            }

        }
        return output.generateSuccessResponse(product, "Success");
    }

    public ResponseDTO getList() {

        List<Product> product = productRepository.findAllByStatus("V");
        if (product == null) {
            return output.generateErrorResponse("No data found");
        } else {
            return output.generateSuccessResponse(product, "Success!");
        }
    }

    public ResponseDTO getListByCategory(String categoryName) {

        if (categoryName != null && !categoryName.equals("")){
            List<Product> product = productRepository.findAllByCategoryAndStatus(categoryName, "V");
            if (product == null) {
                return output.generateErrorResponse("No data found");
            } else {
                return output.generateSuccessResponse(product, "Success!");
            }
        }

        List<Product> products = productRepository.findAllByStatus("V");
        if (products == null) {
            return output.generateErrorResponse("No data found");
        } else {
            return output.generateSuccessResponse(products, "Success!");
        }
    }

    public ResponseDTO getAllProductCategories() {
        List<Product> products = productRepository.findAllByStatus( "V");
        if (products == null) {
            return output.generateErrorResponse("No data found");
        } else {
            return output.generateSuccessResponse(products, "Success!");
        }
    }


    public ResponseDTO getListBySearch(String searchName) {

        if (searchName != null && !searchName.equals("")){
            List<Product> product = productRepository.findAllByNameAndStatus(searchName, "V");
            if (product == null) {
                return output.generateErrorResponse("No data found");
            } else {
                return output.generateSuccessResponse(product, "Success!");
            }
        }

        List<Product> products = productRepository.findAllByStatus("V");
        if (products == null) {
            return output.generateErrorResponse("No data found");
        } else {
            return output.generateSuccessResponse(products, "Success!");
        }
    }


    public ResponseDTO get(long id) {
        Product product = productRepository.findByIdAndStatus(id, "V");
        if (product == null) {
            return output.generateErrorResponse("No data found");

        } else {
            return output.generateSuccessResponse(product, "Success");
        }

    }

    public ResponseDTO update(ProductUpdateDTO input, long id, User requester) {
        Product product;

        if (requester.hasAuthority(Authority.ROLE_ADMIN)) {
            product = productRepository.findByIdAndStatus(id, "V");
            if (product != null) {
                product.setName(input.getName());
                product.setPrice(input.getPrice());
                product.setCategory(input.getCategory());
                product.setQuantity(input.getQuantity());
                product.setStatus("V");
                productRepository.save(product);
                return output.generateSuccessResponse(product, "Successfully updated");
            } else {
                return output.generateErrorResponse("NO data found");
            }

        } else {
            return output.generateErrorResponse("Permission Denied!!");
        }
    }

    public ResponseDTO delete(long id,User requester)
    {
        if (requester.hasAuthority(Authority.ROLE_ADMIN))
        {
            Product product = productRepository.findByIdAndStatus(id, "V");
            if (product == null) {
                return output.generateErrorResponse("You have entered a wrong id");
            } else {
                product.setStatus("D");
                productRepository.save(product);
                return output.generateSuccessResponse(product, "success");
            }

        }else
        {
            return output.generateErrorResponse("Permission Denied!!");
        }

    }
}







