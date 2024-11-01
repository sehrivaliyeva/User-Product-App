package az.codebridge.task.controller;

import az.codebridge.task.dto.ProductRequestDto;
import az.codebridge.task.dto.ProductResponseDto;
import az.codebridge.task.exception.StockException;
import az.codebridge.task.service.ProductService;
import az.codebridge.task.status.ProductStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * 1)Create product(ProductStatus ACTIVE) --> Yeni gelen mehsullarin statusu buna kececek
     * 2)Update product(Statusdan basqa butun fieldlari update elemek ucun istifade edirik)
     * 3)Delete product(@PutMapping isledirik, Id sine gore productu getirib statusunu DELETED cevir)
     * Statusu DELETED olanlar uje istifade edilmir.
     * 4)Get product
     * 5)Get all product(List)
     * 6)BuyProduct(ACTIVE statusunda olan productlari getirib yoxlama aparib sonra almaq islemini tamamlamalisan.
     * Eger ki almaq istediyi miqdar stocda olandan coxdursa o zaman exception atsin. Eger almaq istese o zaman alsin ve
     * Status OUT_OF_STOCK a kecsin; Bazadan miqdar azalsin;
     * 7)ReturnProduct(Aldigi mehsul geri qayitsin ve balance artsin)
     **/

    @PostMapping("/create-product")
    public ProductResponseDto createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    @PutMapping("/update-product/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto productRequestDto) {
        return productService.updateProduct(id, productRequestDto);
    }

    @PutMapping("/soft-delete-product/{id}")
    public void softDeleteProduct(@PathVariable Long id) {
        productService.softDeleteProduct(id);

    }

    @GetMapping("/get-product/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/get-all-products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

   /* @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id, @RequestParam Integer quantity) {
        try {
            productService.buyProduct(id, quantity);
            return "MEHSUL UGURLA ALINDI";
        } catch (StockException e) {
            return "Error: " + e.getMessage();
        } catch (RuntimeException e) {
            return "PROSES UGURSUZ OLDU !!";
        }
    }*/

    @GetMapping("/buy/{product_id}/{user_id}")
    public ProductResponseDto buyProduct(@PathVariable Long product_id, @PathVariable Long user_id) {
        return productService.buyProduct(product_id, user_id);
    }

    @GetMapping("/reverse-product/{product_id}/{user_id}")
    public ProductResponseDto reverseProduct(@PathVariable Long product_id, @PathVariable Long user_id) {
        return productService.reverseProduct(product_id, user_id);
    }


}
