package service;

import dao.ProductDao;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductDao productDao;

    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    public void save(Product product){
        productDao.save(product);
    }
}
