package abstractDAO;

import model.Product;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product>{
    @Override
    public List<Product> findAll() {
        return super.findAll();
    }

    @Override
    public Product findById(int id) {
        return super.findById(id);
    }

    @Override
    public Product insert(Product product) {
        return super.insert(product);
    }

    @Override
    public Product update(Product product) {
        return super.update(product);
    }

}
